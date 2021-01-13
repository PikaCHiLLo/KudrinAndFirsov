package ru.ssau.tk.kaf.kudrinandfirsov.functions.factory;

import static org.testng.Assert.*;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.ArrayTabulatedFunction;
import org.testng.annotations.Test;

public class ArrayTabulatedFunctionFactoryTest {

    final double[] xValues = new double[]{1, 2, 3, 4, 5};
    final double[] yValues = new double[]{2, 4, 6, 8, 10};

    ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    @Test
    public void testArrayTabulatedFunctionFactory() {

        assertEquals(factory.create(xValues, yValues).getClass(), ArrayTabulatedFunction.class);
    }
}