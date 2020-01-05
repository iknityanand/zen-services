package io.github.iknityanand.zenservices.multiplication.service;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.domain.User;
import io.github.iknityanand.zenservices.repository.MultiplicationResultAttemptRepository;
import io.github.iknityanand.zenservices.repository.UserRepository;
import io.github.iknityanand.zenservices.service.MultiplicationServiceImlp;
import io.github.iknityanand.zenservices.service.RandomGeneratorService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
//import org.assertj.core.util.Lists;

public class MultiplicationServiceImplTest {

	private MultiplicationServiceImlp multiplicationServiceImlp;

	@Mock
	private RandomGeneratorService randomGeneratorService;

	@Mock
	private MultiplicationResultAttemptRepository attemptRepository;

	@Mock
	private UserRepository userRepository;

	private JacksonTester<MultiplicationResultAttempt> jsonResultAttempt;
	private JacksonTester<List<MultiplicationResultAttempt>> jsonResultAttemptList;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		multiplicationServiceImlp = new MultiplicationServiceImlp(attemptRepository, userRepository,
				randomGeneratorService);
	}

	@Test
	public void createRandomMultiplicationTest() {
		BDDMockito.given(randomGeneratorService.generatedRandomFactor()).willReturn(50, 30);

		Multiplication multiplication = multiplicationServiceImlp.createRandomMultiplication();

		Assertions.assertThat(multiplication.getFactorA()).isEqualTo(50);
		Assertions.assertThat(multiplication.getFactorB()).isEqualTo(30);
	}

	@Test
	public void checkCorrectAttemptTest() {
		Multiplication multiplication = new Multiplication(50, 60);

		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3000, false);

		MultiplicationResultAttempt verifiedAttempt = new MultiplicationResultAttempt(user, multiplication, 3000, true);

		given(userRepository.findByAlias("john_doe"));
		// when
		boolean attemptResult = multiplicationServiceImlp.checkAttempt(attempt);

		// then

		// assert
		Assertions.assertThat(attemptResult).isTrue();
		verify(attemptRepository).save(verifiedAttempt);
	}

	@Test
	public void checkWrongAttemptTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());

		// when
		boolean attemptResult = multiplicationServiceImlp.checkAttempt(attempt);

		// assert
		Assertions.assertThat(attemptResult).isFalse();
		verify(attemptRepository).save(attempt);

	}

	@Test
	public void retrieveStatsTest() {
		// given
		Multiplication multiplication = new Multiplication(50, 60);
		User user = new User("john_doe");
		MultiplicationResultAttempt attempt1 = new MultiplicationResultAttempt(user, multiplication, 3010, false);
		MultiplicationResultAttempt attempt2 = new MultiplicationResultAttempt(user, multiplication, 3051, false);
//		List<MultiplicationResultAttempt> latestAttempts = Lists.newArrayList(attempt1, attempt2);
		given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
//		given(attemptRepository.findTop5ByUserAliasOrderByIdDesc("john_doe")).willReturn(latestAttempts);

		// when
		List<MultiplicationResultAttempt> latestAttemptsResult = multiplicationServiceImlp.getStatsForUser("john_doe");

		// then
//		Assertions.assertThat(latestAttemptsResult).isEqualTo(latestAttempts);
	}
}
