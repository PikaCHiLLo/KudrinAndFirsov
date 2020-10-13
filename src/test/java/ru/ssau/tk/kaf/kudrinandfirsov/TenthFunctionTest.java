package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.MathFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TenthFunction;

import static org.testng.Assert.*;

public class TenthFunctionTest {

    private final MathFunction tenth=new TenthFunction();

    @Test
    public void testApply() {
        assertEquals(tenth.apply(5),0.5,0.00001);
        assertEquals(tenth.apply(9),0.9,0.00001);
        assertEquals(tenth.apply(7),0.7,0.00001);
    }
}