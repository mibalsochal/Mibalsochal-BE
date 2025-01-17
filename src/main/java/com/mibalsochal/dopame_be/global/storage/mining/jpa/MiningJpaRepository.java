package com.mibalsochal.dopame_be.global.storage.mining.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MiningJpaRepository extends JpaRepository<MiningEntity, Long> {

    @Modifying
    @Query("""
        update MiningEntity set response=:response where id=:miningId
""")
    void updateResponse(Long miningId, String response);

}
