package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.MathFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.SqrFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TenthFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.UnitFunction;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    private final MathFunction sqr = new SqrFunction();
    private final MathFunction tenth = new TenthFunction();
    private final MathFunction unit = new UnitFunction();

    @Test
    public void testAndThen() {
        assertEquals(tenth.andThen(sqr).apply(40), 16.0, 0.1);
        assertEquals(unit.andThen(tenth).apply(7), 0.1, 0.1);
        assertEquals(unit.andThen(tenth).andThen(sqr).apply(67), 0.01, 0.1);
    }
}