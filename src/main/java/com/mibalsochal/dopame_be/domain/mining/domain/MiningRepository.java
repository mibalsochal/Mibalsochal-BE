package com.mibalsochal.dopame_be.domain.mining.domain;

public interface MiningRepository {
    Mining save(Mining mining);

    void updateMining(Long miningId, String response);
}
