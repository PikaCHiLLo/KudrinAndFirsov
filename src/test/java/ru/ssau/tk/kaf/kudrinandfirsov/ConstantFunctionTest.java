package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.ConstantFunction;

import static org.testng.Assert.*;

public class ConstantFunctionTest {

    @Test
    public void testApply() {
        ConstantFunction constant = new ConstantFunction(5);
        assertEquals(constant.apply(1), 5.0, 0.00001);
        assertEquals(constant.apply(-7.3), 5.0, 0.00001);
        assertEquals(constant.apply(12), 5.0, 0.00001);
    }
}