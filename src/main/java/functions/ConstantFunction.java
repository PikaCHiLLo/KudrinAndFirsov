package functions;

public class ConstantFunction implements MathFunction{
    private final double constant;
    public ConstantFunction  (double x1) {
        constant=x1;
    }
    @Override
    public double apply (double x){
        return constant;
    }
}