package functions;

public class RadGradFunction implements MathFunction {
    @Override
    public double apply (double x) {
        double pi=3.14;
        return x*180/pi;
    }
}
