package ru.ssau.tk.kaf.kudrinandfirsov.functions.factory;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
}
