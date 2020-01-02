package io.github.iknityanand.zenservices.service;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiplicationServiceImlp implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    @Autowired
    public MultiplicationServiceImlp(final RandomGeneratorService randomGeneratorService){
        this.randomGeneratorService = randomGeneratorService;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generatedRandomFactor();
        int factorB = randomGeneratorService.generatedRandomFactor();
        return new Multiplication(factorA, factorB);
    }

	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
		// TODO Auto-generated method stub
		return resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA() * 
				resultAttempt.getMultiplication().getFactorB();
	}
}
