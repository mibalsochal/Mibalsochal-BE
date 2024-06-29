package com.mibalsochal.dopame_be.domain.mining.domain.question;

public interface QuestionGenerator {
    QuestionResult generateQuestion(QuestionRequest questionRequest);
}
