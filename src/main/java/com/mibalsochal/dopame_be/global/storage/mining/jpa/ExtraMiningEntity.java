package com.mibalsochal.dopame_be.global.storage.mining.jpa;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class ExtraMiningEntity {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate;
    private String response;

    @ManyToOne(fetch = FetchType.LAZY)
    private MiningEntity miningEntity;
}
