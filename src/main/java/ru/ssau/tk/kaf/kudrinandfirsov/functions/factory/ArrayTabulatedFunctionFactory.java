package ru.ssau.tk.kaf.kudrinandfirsov.functions.factory;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.ArrayTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {

    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}