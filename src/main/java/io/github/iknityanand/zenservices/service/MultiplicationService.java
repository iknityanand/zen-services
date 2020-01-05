package io.github.iknityanand.zenservices.service;

import java.util.List;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

    /*
    * Creates a Multiplication object
    * with two randomly generated factors
    *
    * @return a Multiplication object with random factors
    */
    Multiplication createRandomMultiplication();
    
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
    
    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}
