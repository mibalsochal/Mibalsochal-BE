package com.mibalsochal.dopame_be.global.storage.mining.jpa;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MiningEntity {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate createdDate;
    private String platformUrl;
    private String title;
    private String question;
    private String response;
}
