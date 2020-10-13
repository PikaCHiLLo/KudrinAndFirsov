package ru.ssau.tk.kaf.kudrinandfirsov;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.ZeroFunction;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    public ZeroFunction zero = new ZeroFunction();
    @Test
    public void testZero() {
        assertEquals(zero.apply(1), 0, 0.0001);
        assertEquals(zero.apply(43343), 0, 0.0000001);
        assertEquals(zero.apply(-3234234), 0, 0.000001);
    }
}