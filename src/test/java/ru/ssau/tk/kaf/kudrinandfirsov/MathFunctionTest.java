package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction tenth = new TenthFunction();
    private final MathFunction unit = new UnitFunction();

    @Test
    public void testAndThen() {
        assertEquals(tenth.andThen(sqr).apply(40), 16.0);
        assertEquals(unit.andThen(tenth).apply(7), 0.1);
        assertEquals(unit.andThen(tenth).andThen(sqr).apply(67), 0.01);
    }
}