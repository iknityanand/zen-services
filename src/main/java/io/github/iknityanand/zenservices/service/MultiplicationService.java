package io.github.iknityanand.zenservices.service;

import io.github.iknityanand.zenservices.domain.Multiplication;

public interface MultiplicationService {

    /*
    * Creates a Multiplication object
    * with two randomly generated factors
    *
    * @return a Multiplication object with random factors
    */
    Multiplication createRandomMultiplication();
}
