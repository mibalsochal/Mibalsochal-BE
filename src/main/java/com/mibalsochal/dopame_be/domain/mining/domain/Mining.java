package com.mibalsochal.dopame_be.domain.mining.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mining {
    private Long id;
    private LocalDate createdDate;
    private String platformUrl;
    private String title;
    private String question;
    private String response;
    private List<ExtraMining> extraMinings;
    private List<Tag> tags;
}
