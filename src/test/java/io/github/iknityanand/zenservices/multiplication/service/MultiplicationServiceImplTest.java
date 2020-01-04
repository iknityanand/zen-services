package io.github.iknityanand.zenservices.multiplication.service;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.domain.User;
import io.github.iknityanand.zenservices.repository.MultiplicationResultAttemptRepository;
import io.github.iknityanand.zenservices.repository.UserRepository;
import io.github.iknityanand.zenservices.service.MultiplicationServiceImlp;
import io.github.iknityanand.zenservices.service.RandomGeneratorService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;


public class MultiplicationServiceImplTest {

    private MultiplicationServiceImlp multiplicationServiceImlp;

    @Mock
    private RandomGeneratorService randomGeneratorService;
    
    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;
    
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImlp = new MultiplicationServiceImlp(randomGeneratorService, attemptRepository, userRepository);
    }

    @Test
    public void createRandomMultiplicationTest(){
        BDDMockito.given(randomGeneratorService.generatedRandomFactor()).willReturn(50,30);

        Multiplication multiplication=multiplicationServiceImlp.createRandomMultiplication();

        Assertions.assertThat(multiplication.getFactorA()).isEqualTo(50);
        Assertions.assertThat(multiplication.getFactorB()).isEqualTo(30);
    }
    
    @Test
    public void checkCorrectAttemptTest() {
    	Multiplication multiplication=new Multiplication(50, 60);
    	
    	User user=new User("john_doe");
    	MultiplicationResultAttempt attempt=new MultiplicationResultAttempt(user, multiplication, 3000, false);
    	
    	MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);
    	
    	given(userRepository.findByAlias("john_doe"));
    	//when
    	boolean attemptResult = multiplicationServiceImlp.checkAttempt(attempt);
    	
    	
    	//then
    	
    	//assert
    	Assertions.assertThat(attemptResult).isTrue();
    	verify(attemptRepository).save(verifiedAttempt);
    }
    
    @Test
    public void checkWrongAttemptTest() {
    	//given
    	Multiplication multiplication=new Multiplication(50,60);
    	User user=new User("john_doe");
    	MultiplicationResultAttempt attempt=new MultiplicationResultAttempt(user, multiplication, 3010, false);
    	given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
    	
    	//when
    	boolean attemptResult = multiplicationServiceImlp.checkAttempt(attempt);
    	
    	//assert
    	Assertions.assertThat(attemptResult).isFalse();
    	verify(attemptRepository).save(attempt);
    	
    }
}
