package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.ArrayTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    private final static double error = 0.00001;

    @Test
    public void testDerive() {
        TabulatedFunction tempList = new LinkedListTabulatedFunction(new double[]{5, 6, 7, 8, 9}, new double[]{25, 36, 49, 64, 81});
        TabulatedDifferentialOperator differentialListOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        tempList = differentialListOperator.derive(tempList);
        assertTrue(tempList instanceof LinkedListTabulatedFunction);

        for (int i = 0; i < tempList.getCount(); i++) {
            assertEquals(tempList.getX(i), (5 + i), error);
        }

        assertEquals(tempList.getY(0), 11, error);
        assertEquals(tempList.getY(1), 13, error);
        assertEquals(tempList.getY(2), 15, error);
        assertEquals(tempList.getY(3), 17, error);
        assertEquals(tempList.getY(4), 17, error);


        TabulatedFunction tempArray = new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5, 6}, new double[]{10, 50, 80, 160, 250, 360});
        TabulatedDifferentialOperator differentialArrayOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        tempArray = differentialArrayOperator.derive(tempArray);
        assertTrue(tempArray instanceof ArrayTabulatedFunction);
        for (int i = 0; i < tempArray.getCount(); i++) {
            assertEquals(tempArray.getX(i), i + 1, error);
        }

        assertEquals(tempArray.getY(0), 40, error);
        assertEquals(tempArray.getY(1), 30, error);
        assertEquals(tempArray.getY(2), 80, error);
        assertEquals(tempArray.getY(3), 90, error);
        assertEquals(tempArray.getY(4), 110, error);
        assertEquals(tempArray.getY(5), 110, error);
    }
}