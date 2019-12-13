package io.github.iknityanand.zenservices.domain;

public class Multiplication {

    // Both Factors
    private int factorA;
    private int factorB;

    // The result of the operation A*B
    private int result;

    public Multiplication(int factorA, int factorB, int result) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = result;
    }

    public int getFactorA() {
        return factorA;
    }

    public void setFactorA(int factorA) {
        this.factorA = factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public void setFactorB(int factorB) {
        this.factorB = factorB;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Multiplication{" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result=" + result +
                '}';
    }
}
