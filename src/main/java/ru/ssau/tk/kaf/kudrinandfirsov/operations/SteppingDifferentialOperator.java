package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
        if(step <= 0 || step == Double.POSITIVE_INFINITY || Double.isNaN(step)) {
            throw new IllegalArgumentException("Шаг не соответствует требованиям.");
        }
    }

    public double getStep() {
        return step;
    }
    public void setStep(double step) {
        this.step = step;
    }

    public abstract MathFunction derive(MathFunction function);
}