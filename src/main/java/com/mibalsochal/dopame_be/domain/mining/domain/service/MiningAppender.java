package com.mibalsochal.dopame_be.domain.mining.domain.service;

import org.springframework.stereotype.Service;

import com.mibalsochal.dopame_be.domain.mining.domain.Mining;
import com.mibalsochal.dopame_be.domain.mining.domain.MiningRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MiningAppender {
    private final MiningRepository miningRepository;

    public Long appendMiningAndGetId(Mining mining){
        return miningRepository.save(mining).getId();
    }
}
