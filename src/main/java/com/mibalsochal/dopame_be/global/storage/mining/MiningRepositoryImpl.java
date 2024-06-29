package com.mibalsochal.dopame_be.global.storage.mining;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mibalsochal.dopame_be.domain.mining.domain.Mining;
import com.mibalsochal.dopame_be.domain.mining.domain.MiningRepository;
import com.mibalsochal.dopame_be.domain.mining.domain.Tag;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.ExtraMiningJpaRepository;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.MiningEntity;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.MiningJpaRepository;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.MiningTagEntity;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.MiningTagJpaRepository;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.TagEntity;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.TagJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MiningRepositoryImpl implements MiningRepository {
	private final MiningJpaRepository miningJpaRepository;
	private final ExtraMiningJpaRepository extraMiningJpaRepository;
	private final MiningTagJpaRepository miningTagJpaRepository;
	private final TagJpaRepository tagJpaRepository;
	private final MiningMapper miningMapper;

	@Override
	public Mining save(Mining mining) {
		MiningEntity miningEntity = miningMapper.toEntity(mining);
		miningJpaRepository.save(miningEntity);

		// extra mining은 mining이 생성될 때, 없음.

		// tag는 중간 테이블이 필요하기 때문에, tag의 내용을 조회하면서, tag가 없으면 생성하고, tag가 있으면 tag를 가져와서 저장한다.

		final List<String> tagDescriptionList = mining.getTags().stream()
			.map(Tag::getDescription)
			.toList();

		List<TagEntity> tagEntities = tagJpaRepository.findAllByDescriptionsIn(tagDescriptionList);

		List<TagEntity> newTagEntities = tagDescriptionList.stream()
			.filter(tagDescription -> tagEntities.stream()
				.noneMatch(tagEntity -> tagEntity.getDescription().equals(tagDescription)))
			.map(tagDescription -> {
				TagEntity newTag = new TagEntity(null, tagDescription);
				return tagJpaRepository.save(newTag);
			})
			.toList();

		tagEntities.addAll(newTagEntities);

		tagEntities.forEach(tagEntity -> {
			MiningTagEntity miningTagEntity = new MiningTagEntity(null, miningEntity, tagEntity);
			miningTagJpaRepository.save(miningTagEntity);
		});

		return miningMapper.toDomain(miningEntity, List.of(), tagEntities);
	}

	@Override
	public void updateMining(Long miningId, String response) {
		miningJpaRepository.updateResponse(miningId, response);
	}

}
