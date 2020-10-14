package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.UnitFunction;

import static org.testng.Assert.*;

public class UnitFunctionTest {

    UnitFunction unit = new UnitFunction();

    @Test
    public void testZero() {
        assertEquals(unit.apply(1), 1, 0.0001);
        assertEquals(unit.apply(43343), 1, 0.0000001);
        assertEquals(unit.apply(-3234234), 1, 0.000001);
    }
}