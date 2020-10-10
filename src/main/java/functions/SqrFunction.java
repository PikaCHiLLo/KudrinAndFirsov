package functions;

import java.lang.Math;

public class SqrFunction implements MathFunction{
    @Override
    public double apply (double x) {
        double y=2;
        return Math.pow(x,y);
    }
}
