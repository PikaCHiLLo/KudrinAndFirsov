package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.MathFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.RadGradFunction;

import static org.testng.Assert.*;

public class RadGradFunctionTest {

    private final MathFunction radgrad=new RadGradFunction();
    @Test
    public void testApply() {
        float pi= (float) 3.14;
        assertEquals(radgrad.apply(1),57.29577951,0.00001);
        assertEquals(radgrad.apply(pi),180,0.1);
    }
}