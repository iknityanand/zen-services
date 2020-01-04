package io.github.iknityanand.zenservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;
import io.github.iknityanand.zenservices.service.MultiplicationService;

@RestController
@RequestMapping("/results")
public final class MultiplicationResultAttemptController {

	private final MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
		super();
		this.multiplicationService = multiplicationService;
	}
	
	@PostMapping
	ResponseEntity<MultiplicationResultAttempt> postResult(@RequestBody MultiplicationResultAttempt attempt){
boolean isCorrect = multiplicationService.checkAttempt(attempt);
MultiplicationResultAttempt attemptCopy = new MultiplicationResultAttempt(attempt.getUser(), attempt.getMultiplication(), attempt.getResultAttempt(), isCorrect);
		return ResponseEntity.ok(attemptCopy);
		
		
	}

}
