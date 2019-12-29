package io.github.iknityanand.zenservices.multiplication.service;

import io.github.iknityanand.zenservices.service.RandomGeneratorServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomGeneratorServiceImplTest {

    private RandomGeneratorServiceImpl randomGeneratorServiceImpl;

    @Before
    public void setUp(){
        randomGeneratorServiceImpl = new RandomGeneratorServiceImpl();
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() {

        List<Integer> randomFactors = IntStream.range(0, 1000).map(i -> randomGeneratorServiceImpl.generatedRandomFactor()).boxed().collect(Collectors.toList());

        Assertions.assertThat(randomFactors).containsOnlyElementsOf(IntStream.range(11, 100).boxed().collect(Collectors.toList()));

    }
}
