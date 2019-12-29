package io.github.iknityanand.zenservices.multiplication.service;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.service.MultiplicationService;
import io.github.iknityanand.zenservices.service.RandomGeneratorService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplicationServiceTest {

    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplicationTest(){

        int randomFactor = randomGeneratorService.generatedRandomFactor();
        given(randomFactor);

        willReturn(50,30);

        Multiplication multiplication= multiplicationService.createRandomMultiplication();

        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
