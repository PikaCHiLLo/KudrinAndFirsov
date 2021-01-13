package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.SqrFunction;

import static org.testng.Assert.*;

public class MiddleSteppingDifferentialOperatorTest {
    private final static double error = 0.00001;

    @Test
    public void testDerive() {
        double step = 0.001;

        MiddleSteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 4, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 8, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 12, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(4), 16, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 20, error);

    }
}