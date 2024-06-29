package com.mibalsochal.dopame_be.domain.mining.domain.service;

import org.springframework.stereotype.Service;

import com.mibalsochal.dopame_be.domain.mining.domain.MiningRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MiningModifier {
	private final MiningRepository miningRepository;

	public void updateMiningResponse(Long miningId, String response) {
		miningRepository.updateMining(miningId, response);
	}
}
