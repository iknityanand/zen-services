package io.github.iknityanand.zenservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.iknityanand.zenservices.domain.Multiplication;
import io.github.iknityanand.zenservices.service.MultiplicationService;

@RestController
public class MultiplicationController {

	private final MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationController(MultiplicationService multiplicationService) {
		super();
		this.multiplicationService = multiplicationService;
	}

	@GetMapping("/random")
	Multiplication getRandomMultiplication() {
		return multiplicationService.createRandomMultiplication();
	}

}
