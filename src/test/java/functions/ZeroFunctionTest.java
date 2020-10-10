package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ZeroFunctionTest {

    private final ZeroFunction zero = new ZeroFunction();
    @Test
    public void testZero() {
        assertEquals(zero.apply(1), 0, 0.0001);
        assertEquals(zero.apply(43343), 0, 0.0000001);
        assertEquals(zero.apply(-3234234), 0, 0.000001);
    }
}