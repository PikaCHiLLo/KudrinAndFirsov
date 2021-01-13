package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}