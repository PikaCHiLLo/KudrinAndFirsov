package functions;

public class UnitFunction extends ConstantFunction{
    public UnitFunction(){
        this(1);
    }
    public UnitFunction(double x){
        super(1);
    }
    public double apply(double x){
        return 1;
    }

}
