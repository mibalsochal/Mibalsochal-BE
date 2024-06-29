package com.mibalsochal.dopame_be.domain.mining.presentation;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mibalsochal.dopame_be.domain.mining.application.dto.MiningDescriptionRequest;
import com.mibalsochal.dopame_be.domain.mining.application.dto.PlatformDetailsRequest;
import com.mibalsochal.dopame_be.domain.mining.application.dto.PlatformDetailsResponse;
import com.mibalsochal.dopame_be.domain.mining.application.service.MiningService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MiningController {

	private final MiningService miningService;

	@PostMapping("/mining")
	public PlatformDetailsResponse appendMining(@RequestBody PlatformDetailsRequest request) {
		return miningService.createMining(request.getPlatformUrl());
	}

	@PatchMapping("/mining/{miningId}")
	public void updateQuestionAnswer(
		@RequestBody MiningDescriptionRequest request,
		@PathVariable Long miningId) {
		miningService.updateMining(request, miningId);
	}

}
