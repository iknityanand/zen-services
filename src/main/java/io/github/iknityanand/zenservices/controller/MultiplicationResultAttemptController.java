package io.github.iknityanand.zenservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.iknityanand.zenservices.service.MultiplicationService;

@RestController
@RequestMapping("/results")
final class MultiplicationResultAttemptController {

	private final MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationResultAttemptController(MultiplicationService multiplicationService) {
		super();
		this.multiplicationService = multiplicationService;
	}

	// Here we will implement our post later

	static final class ResultResponse {
		private final boolean correct;

		public ResultResponse(boolean correct) {
			super();
			this.correct = correct;
		}

		public boolean isCorrect() {
			return correct;
		}

		public ResultResponse() {
			this.correct = false;
			// TODO Auto-generated constructor stub
		}		
	}
}
