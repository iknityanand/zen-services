package io.github.iknityanand.zenservices.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class User {
	
	private final String alias;
	public User() {
		this.alias = null;
	}	
}
