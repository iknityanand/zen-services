package io.github.iknityanand.zenservices.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class represents a Multiplication in our application.
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Multiplication {

	// Both factors
    private final int factorA;
    private final int factorB;

    // Empty constructor for JSON (de)serialization
    Multiplication() {
        this(0, 0);
    }
}