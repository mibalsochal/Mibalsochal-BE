package com.mibalsochal.dopame_be.global.storage.mining;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mibalsochal.dopame_be.domain.mining.domain.ExtraMining;
import com.mibalsochal.dopame_be.domain.mining.domain.Mining;
import com.mibalsochal.dopame_be.domain.mining.domain.Tag;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.ExtraMiningEntity;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.MiningEntity;
import com.mibalsochal.dopame_be.global.storage.mining.jpa.TagEntity;

@Component
public class MiningMapper {

	public MiningEntity toEntity(Mining mining) {
		return new MiningEntity(
			mining.getId(),
			mining.getCreatedDate(),
			mining.getPlatformUrl(),
			mining.getTitle(),
			mining.getQuestion(),
			mining.getResponse()
		);
	}

	public Mining toDomain(MiningEntity miningEntity, List<ExtraMiningEntity> extraMinings,
		List<TagEntity> tagEntities) {
		return new Mining(
			miningEntity.getId(),
			miningEntity.getCreatedDate(),
			miningEntity.getPlatformUrl(),
			miningEntity.getTitle(),
			miningEntity.getQuestion(),
			miningEntity.getResponse(),
			toExtraMining(extraMinings),
			toTag(tagEntities)
		);
	}

	private List<ExtraMining> toExtraMining(List<ExtraMiningEntity> extraMiningEntities) {
		return extraMiningEntities.stream()
			.map(extraMiningEntity -> new ExtraMining(
				extraMiningEntity.getCreatedDate(),
				extraMiningEntity.getResponse()
			))
			.toList();
	}

	private List<Tag> toTag(List<TagEntity> tagEntities) {
		return tagEntities.stream()
			.map(tagEntity -> new Tag(
				tagEntity.getDescription()
			))
			.toList();
	}
}
