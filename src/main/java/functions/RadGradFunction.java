package functions;

public class RadGradFunction implements MathFunction {
    @Override
    public double apply (double x) {
        return Math.toDegrees(x);
    }
}
