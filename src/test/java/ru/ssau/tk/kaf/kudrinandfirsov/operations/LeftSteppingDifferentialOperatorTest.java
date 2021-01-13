package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.SqrFunction;

import static org.testng.Assert.*;

public class LeftSteppingDifferentialOperatorTest {
    private final static double error = 0.00001;

    @Test
    public void testDerive() {
        double step = 0.001;

        LeftSteppingDifferentialOperator differentialOperator = new LeftSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 1.999, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(2), 3.999, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.999, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(4), 7.999, error);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(5), 9.999, error);
    }
}