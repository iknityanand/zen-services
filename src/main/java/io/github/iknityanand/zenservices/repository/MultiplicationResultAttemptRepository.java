package io.github.iknityanand.zenservices.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.github.iknityanand.zenservices.domain.MultiplicationResultAttempt;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

	/*
	 * return the latest 5 attempt for the given user,
	 * identified by their alias
	 */
	List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}