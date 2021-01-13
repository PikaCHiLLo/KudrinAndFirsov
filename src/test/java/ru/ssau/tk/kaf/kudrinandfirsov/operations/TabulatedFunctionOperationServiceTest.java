package ru.ssau.tk.kaf.kudrinandfirsov.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.ArrayTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.Point;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    private final static double error = 0.00001;
    private final double[] xValues = new double[]{-3, -2, -1, 0, 1, 2, 3};
    private final double[] yValuesArray = new double[]{-9, -4, -1, 0, 1, 4, 9};
    private final double[] yValuesList = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final TabulatedFunctionOperationService operationServiceArray = new TabulatedFunctionOperationService();
    private final TabulatedFunctionOperationService operationServiceList = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());

    private final TabulatedFunction testArrayFunction = new ArrayTabulatedFunction(xValues, yValuesArray);
    private final TabulatedFunction testListFunction = new LinkedListTabulatedFunction(xValues, yValuesList);

    @Test
    public void testAsPoints() {
        Point[] Dots = TabulatedFunctionOperationService.asPoints(testArrayFunction);
        int i = 0;
        for (Point myPoint : Dots) {
            assertEquals(myPoint.x, testArrayFunction.getX(i), error);
            assertEquals(myPoint.y, testArrayFunction.getY(i++), error);
        }
        assertEquals(testArrayFunction.getCount(), i);

        Dots = TabulatedFunctionOperationService.asPoints(testListFunction);
        i = 0;
        for (Point myPoint : Dots) {
            assertEquals(myPoint.x, testListFunction.getX(i), error);
            assertEquals(myPoint.y, testListFunction.getY(i++), error);
        }
        assertEquals(testListFunction.getCount(), i);
    }

    @Test
    public void testGetFactory_testSetFactory() {
        assertTrue(operationServiceArray.getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue(operationServiceList.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSum() {
        final double[] xValuesErr1 = new double[]{-3, -2, -1, 0, 1, 2};
        final double[] yValuesErr1 = new double[]{-9, -4, -1, 0, 1, 4};
        TabulatedFunction errorTest1 = new ArrayTabulatedFunction(xValuesErr1, yValuesErr1);
        assertThrows(InconsistentFunctionsException.class, () -> operationServiceList.sum(testListFunction, errorTest1));

        final double[] xValuesErr2 = new double[]{-4, -2, -1, 0, 1, 2, 3};
        TabulatedFunction errorTest2 = new ArrayTabulatedFunction(xValuesErr2, yValuesArray);
        assertThrows(InconsistentFunctionsException.class, () -> operationServiceList.sum(testListFunction, errorTest2));

        TabulatedFunction testSumOfArrays = operationServiceArray.sum(testArrayFunction, testArrayFunction);
        assertTrue(testSumOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSumOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] + yValuesArray[i++]);
        }
        TabulatedFunction testSumOfLists = operationServiceList.sum(testListFunction, testListFunction);
        assertTrue(testSumOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSumOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] + yValuesList[i++]);
        }
        TabulatedFunction testSumOfArrayAndList = operationServiceArray.sum(testArrayFunction, testListFunction);
        assertTrue(testSumOfArrayAndList instanceof ArrayTabulatedFunction);
        i = 0;
        for (Point point : testSumOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] + yValuesList[i++]);
        }
    }

    @Test
    public void testSubtract() {
        TabulatedFunction testSubtractOfArrays = operationServiceArray.subtract(testArrayFunction, testArrayFunction);
        assertTrue(testSubtractOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testSubtractOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] - yValuesArray[i++]);
        }

        TabulatedFunction testSubtractOfLists = operationServiceList.subtract(testListFunction, testListFunction);
        i = 0;
        for (Point point : testSubtractOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] - yValuesList[i++]);
        }
        assertTrue(testSubtractOfLists instanceof LinkedListTabulatedFunction);

        TabulatedFunction testSubtractOfArrayAndList = operationServiceList.subtract(testArrayFunction, testListFunction);
        assertTrue(testSubtractOfArrayAndList instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testSubtractOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] - yValuesList[i++]);
        }

    }

    @Test
    public void testMultiplication() {
        TabulatedFunction testMultiplicationOfArrays = operationServiceArray.multiplication(testArrayFunction, testArrayFunction);
        assertTrue(testMultiplicationOfArrays instanceof ArrayTabulatedFunction);

        int i = 0;
        for (Point point : testMultiplicationOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesArray[i++]);
        }

        TabulatedFunction testMultiplicationOfLists = operationServiceList.multiplication(testListFunction, testListFunction);
        assertTrue(testMultiplicationOfLists instanceof LinkedListTabulatedFunction);
        i = 0;
        for (Point point : testMultiplicationOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] * yValuesList[i++]);
        }

        TabulatedFunction testMultiplicationOfArrayAndList = operationServiceArray.multiplication(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testMultiplicationOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] * yValuesList[i++]);
        }
        assertTrue(testMultiplicationOfArrayAndList instanceof ArrayTabulatedFunction);
    }

    @Test
    public void testDivision() {
        TabulatedFunction testDivisionOfArrays = operationServiceArray.division(testArrayFunction, testArrayFunction);
        assertTrue(testDivisionOfArrays instanceof ArrayTabulatedFunction);
        int i = 0;
        for (Point point : testDivisionOfArrays) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] / yValuesArray[i++]);
        }

        TabulatedFunction testDivisionOfLists = operationServiceList.division(testListFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfLists) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesList[i] / yValuesList[i++]);
        }
        assertTrue(testDivisionOfLists instanceof LinkedListTabulatedFunction);
        TabulatedFunction testDivisionOfArrayAndList = operationServiceArray.division(testArrayFunction, testListFunction);
        i = 0;
        for (Point point : testDivisionOfArrayAndList) {
            assertEquals(point.x, xValues[i]);
            assertEquals(point.y, yValuesArray[i] / yValuesList[i++]);
        }
        assertTrue(testDivisionOfArrayAndList instanceof ArrayTabulatedFunction);
    }
}