package io.github.iknityanand.zenservices.service;

import java.util.Random;

public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    final static int MINIMUM_FACTOR = 11;
    final static int MAXIMUM_FACTOR = 99;

    @Override
    public int generatedRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MAXIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
