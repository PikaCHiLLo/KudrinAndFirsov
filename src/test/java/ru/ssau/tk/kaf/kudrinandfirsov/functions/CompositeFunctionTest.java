package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.CompositeFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.SqrFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TenthFunction;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final CompositeFunction composite1 = new CompositeFunction(new TenthFunction(), new SqrFunction());

    private final CompositeFunction composite2 = new CompositeFunction(composite1, new SqrFunction());

    private final CompositeFunction composite3 = new CompositeFunction(composite2, new ZeroFunction());

    @Test
    public void testFunction() {
        assertEquals(composite1.apply(50), 25, 0.000001);
        assertEquals(composite1.apply(60), 36, 0.00001);
        assertEquals(composite1.apply(40), 16, 0.00001);
        assertEquals(composite2.apply(10), 1, 0.000001);
        assertEquals(composite2.apply(20), 16, 0.00001);
        assertEquals(composite2.apply(-30), 81, 0.00001);
        assertEquals(composite3.apply(66), 0, 0.00001);
        assertEquals(composite3.apply(12), 0, 0.00001);
        assertEquals(composite3.apply(87), 0, 0.00001);
    }
}