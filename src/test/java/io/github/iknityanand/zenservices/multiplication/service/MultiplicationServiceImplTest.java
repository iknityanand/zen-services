package io.github.iknityanand.zenservices.multiplication.service;

import static org.junit.Assert.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.domain.User;
import io.github.iknityanand.zenservices.service.MultiplicationServiceImlp;
import io.github.iknityanand.zenservices.service.RandomGeneratorService;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImlp multiplicationServiceImlp;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImlp = new MultiplicationServiceImlp(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplicationTest(){
        BDDMockito.given(randomGeneratorService.generatedRandomFactor()).willReturn(50,30);

        Multiplication multiplication=multiplicationServiceImlp.createRandomMultiplication();

        Assertions.assertThat(multiplication.getFactorA()).isEqualTo(50);
        Assertions.assertThat(multiplication.getFactorB()).isEqualTo(30);
        Assertions.assertThat(multiplication.getResult()).isEqualTo(1500);
    }
    
    @Test
    public void checkCorrectAttemptTest() {
    	Multiplication multiplication=new Multiplication(50, 60);
    	
    	User user=new User("John_Doe");
    	MultiplicationResultAttempt resultAttempt=new MultiplicationResultAttempt(user, multiplication, 3000);
    	
    	//when
    	boolean attemptResult = multiplicationServiceImlp.checkAttempt(resultAttempt);
    	
    	//assert
    	Assertions.assertThat(attemptResult).isTrue();
    }
    
    @Test
    public void checkWrongAttemptTest() {
    	//given
    	Multiplication multiplication=new Multiplication(50,60);
    	User user=new User("John_Doe");
    	MultiplicationResultAttempt attempt=new MultiplicationResultAttempt(user, multiplication, 3010);
    	
    	//when
    	boolean attemptResult = multiplicationServiceImlp.checkAttempt(attempt);
    	
    	//assert
    	Assertions.assertThat(attemptResult).isFalse();
    	
    	
    }
}
