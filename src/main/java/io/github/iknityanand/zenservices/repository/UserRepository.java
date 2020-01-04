package io.github.iknityanand.zenservices.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.github.iknityanand.zenservices.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByAlias(final String alias);
}
