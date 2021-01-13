package ru.ssau.tk.kaf.kudrinandfirsov.functions.factory;

import static org.testng.Assert.*;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.LinkedListTabulatedFunction;
import org.testng.annotations.Test;

public class LinkedListTabulatedFunctionFactoryTest {

    final double[] xValues = new double[]{1, 2, 3, 4, 5};
    final double[] yValues = new double[]{2, 4, 6, 8, 10};

    LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();

    @Test
    public void testLinkedListTabulatedFunctionFactory() {

        assertEquals(factory.create(xValues, yValues).getClass(), LinkedListTabulatedFunction.class);
    }

}