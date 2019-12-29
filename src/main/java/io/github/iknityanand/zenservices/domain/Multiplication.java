package io.github.iknityanand.zenservices.domain;

/**
 * This class represents a Multiplication in our application.
 */
public class Multiplication {

    // Both factors
    private int factorA;
    private int factorB;

    // The result of the operation A * B
    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }
    
	public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result(A*B)=" + result +
                '}';
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + factorA;
		result = prime * result + factorB;
		result = prime * result + this.result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Multiplication other = (Multiplication) obj;
		if (factorA != other.factorA)
			return false;
		if (factorB != other.factorB)
			return false;
		if (result != other.result)
			return false;
		return true;
	}
    
    

}