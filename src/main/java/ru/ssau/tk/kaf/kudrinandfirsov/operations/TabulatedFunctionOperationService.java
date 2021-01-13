package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.Point;

public class TabulatedFunctionOperationService {
    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] dots = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            dots[i++] = newPoint;
        }
        return dots;
    }

}