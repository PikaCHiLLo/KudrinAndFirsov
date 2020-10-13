package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import java.lang.Math;

public class SqrFunction implements MathFunction{
    @Override
    public double apply (double x) {
        return Math.pow(x,2);
    }
}
