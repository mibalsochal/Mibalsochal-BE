package com.mibalsochal.dopame_be.global.storage.mining.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TagJpaRepository extends JpaRepository<TagEntity, Long> {



    @Query("""
            select t
            from TagEntity t
            where t.description in :descriptions
            """)
    List<TagEntity> findAllByDescriptionsIn(List<String> descriptions);
}
