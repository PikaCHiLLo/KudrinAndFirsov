package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RadGradFunctionTest {

    private final MathFunction radgrad=new RadGradFunction();
    @Test
    public void testApply() {
        float pi= (float) 3.14;
        assertEquals(radgrad.apply(1),57.3248407,0.00001);
        assertEquals(radgrad.apply(pi),180,0.00001);
    }
}