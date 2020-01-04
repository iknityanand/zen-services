package io.github.iknityanand.zenservices.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.domain.User;
import io.github.iknityanand.zenservices.repository.MultiplicationResultAttemptRepository;
import io.github.iknityanand.zenservices.repository.UserRepository;

@Service
public class MultiplicationServiceImlp implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;
	private MultiplicationResultAttemptRepository attemptRepository;
	private UserRepository userRepository;

	@Autowired
	public MultiplicationServiceImlp(MultiplicationResultAttemptRepository attemptRepository,
			UserRepository userRepository, RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
		this.attemptRepository = attemptRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		int factorA = randomGeneratorService.generatedRandomFactor();
		int factorB = randomGeneratorService.generatedRandomFactor();
		return new Multiplication(factorA, factorB);
	}

	@Override
	@Transactional
	public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
		// check if user already exists for that alias
		Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

		boolean isCorrect = attempt.getResultAttempt() == attempt.getMultiplication().getFactorA()
				* attempt.getMultiplication().getFactorB();

		Assert.isTrue(!attempt.isCorrect(), "You cant send an attept marked as correct");

		MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(attempt.getUser(),
				attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);

		// store the attempt
		attemptRepository.save(checkedAttempt);

		return isCorrect;
	}
}
