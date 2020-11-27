package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    private final static double error = 0.0005;
    private final double[] xValues = new double[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
    private final double[] yValues = new double[]{-6, -4, -2, 0, 2, 4, 6, 8, 10};
    private final MathFunction tenthFunction = new TenthFunction();
    ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
    ArrayTabulatedFunction arrayTabulatedMathFunction = new ArrayTabulatedFunction(tenthFunction, 1, 9, 17);
    private final double[] xValues1 = new double[]{5};
    private final double[] yValues1 = new double[]{10};
    ArrayTabulatedFunction arrayTabulatedUnitArrayFunction = new ArrayTabulatedFunction(xValues1, yValues1);
    ArrayTabulatedFunction arrayTabulatedMathChangeFromToFunction = new ArrayTabulatedFunction(tenthFunction, 9, 1, 17);

    @Test
    public void testGetCount() {
        assertEquals(arrayTabulatedFunction.getCount(), 9, error);
        assertEquals(arrayTabulatedMathFunction.getCount(), 17, error);
        assertEquals(arrayTabulatedUnitArrayFunction.getCount(), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getCount(), 17, error);
    }

    @Test
    public void testGetX() {
        assertEquals(arrayTabulatedFunction.getX(0), -3, error);
        assertEquals(arrayTabulatedFunction.getX(2), -1, error);
        assertEquals(arrayTabulatedFunction.getX(4), 1, error);
        assertEquals(arrayTabulatedMathFunction.getX(0), 1, error);
        assertEquals(arrayTabulatedMathFunction.getX(2), 2, error);
        assertEquals(arrayTabulatedMathFunction.getX(4), 3, error);
        assertEquals(arrayTabulatedUnitArrayFunction.getX(0), 5, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getX(0), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getX(2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getX(4), 3, error);
    }

    @Test
    public void testGetY() {
        assertEquals(arrayTabulatedFunction.getY(0), -6, error);
        assertEquals(arrayTabulatedFunction.getY(2), -2, error);
        assertEquals(arrayTabulatedFunction.getY(4), 2, error);
        assertEquals(arrayTabulatedMathFunction.getY(0), 0.1, error);
        assertEquals(arrayTabulatedMathFunction.getY(2), 0.2, error);
        assertEquals(arrayTabulatedMathFunction.getY(4), 0.3, error);
        assertEquals(arrayTabulatedUnitArrayFunction.getY(0), 10, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getY(0), 0.1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getY(2), 0.2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getY(4), 0.3, error);
    }

    @Test
    public void testSetY() {
        arrayTabulatedFunction.setY(0, -6);
        assertEquals(arrayTabulatedFunction.getY(0), -6, error);
        arrayTabulatedMathFunction.setY(1, -6);
        assertEquals(arrayTabulatedMathFunction.getY(1), -6, error);
        arrayTabulatedUnitArrayFunction.setY(0, 7);
        assertEquals(arrayTabulatedUnitArrayFunction.getY(0), 7, error);
        arrayTabulatedMathChangeFromToFunction.setY(1, -6);
        assertEquals(arrayTabulatedMathChangeFromToFunction.getY(1), -6, error);
    }

    @Test
    public void testLeftBound() {
        assertEquals(arrayTabulatedFunction.leftBound(), -3, error);
        assertEquals(arrayTabulatedMathFunction.leftBound(), 1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.leftBound(), 5, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.leftBound(), 1, error);
    }

    @Test
    public void testRightBound() {
        assertEquals(arrayTabulatedFunction.rightBound(), 5, error);
        assertEquals(arrayTabulatedMathFunction.rightBound(), 9, error);
        assertEquals(arrayTabulatedUnitArrayFunction.rightBound(), 5, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.rightBound(), 9, error);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(arrayTabulatedFunction.indexOfX(0), 3, error);
        assertEquals(arrayTabulatedFunction.indexOfX(3), 6, error);
        assertEquals(arrayTabulatedFunction.indexOfX(7), -1, error);
        assertEquals(arrayTabulatedMathFunction.indexOfX(2), 2, error);
        assertEquals(arrayTabulatedMathFunction.indexOfX(4), 6, error);
        assertEquals(arrayTabulatedMathFunction.indexOfX(20), -1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfX(5), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfX(6), -1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfX(4), -1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfX(2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfX(4), 6, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfX(20), -1, error);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(arrayTabulatedFunction.indexOfY(0), 3, error);
        assertEquals(arrayTabulatedFunction.indexOfY(6), 6, error);
        assertEquals(arrayTabulatedFunction.indexOfY(14), -1, error);
        assertEquals(arrayTabulatedMathFunction.indexOfY(0.2), 2, error);
        assertEquals(arrayTabulatedMathFunction.indexOfY(0.4), 6, error);
        assertEquals(arrayTabulatedMathFunction.indexOfY(2), -1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfY(10), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfY(9), -1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.indexOfY(11), -1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfY(0.2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfY(0.4), 6, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.indexOfY(2), -1, error);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(arrayTabulatedFunction.floorIndexOfX(0.1), 3, error);
        assertEquals(arrayTabulatedFunction.floorIndexOfX(3.5), 6, error);
        assertEquals(arrayTabulatedFunction.floorIndexOfX(7), 8, error);
        assertEquals(arrayTabulatedFunction.floorIndexOfX(-7), 0, error);
        assertEquals(arrayTabulatedFunction.floorIndexOfX(-2.5), 0, error);
        assertEquals(arrayTabulatedMathFunction.floorIndexOfX(1.6), 1, error);
        assertEquals(arrayTabulatedMathFunction.floorIndexOfX(3.8), 5, error);
        assertEquals(arrayTabulatedMathFunction.floorIndexOfX(10), 16, error);
        assertEquals(arrayTabulatedMathFunction.floorIndexOfX(0), 0, error);
        assertEquals(arrayTabulatedMathFunction.floorIndexOfX(1.1), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.floorIndexOfX(5.1), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.floorIndexOfX(4.9), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.floorIndexOfX(6), 0, error);
        assertEquals(arrayTabulatedUnitArrayFunction.floorIndexOfX(4), 0, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.floorIndexOfX(1.6), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.floorIndexOfX(3.8), 5, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.floorIndexOfX(10), 16, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.floorIndexOfX(0), 0, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.floorIndexOfX(1.1), 0, error);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(arrayTabulatedFunction.extrapolateLeft(-4), -8, error);
        assertEquals(arrayTabulatedMathFunction.extrapolateLeft(-1), -0.1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.extrapolateLeft(4), 10, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.extrapolateLeft(-1), -0.1, error);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(arrayTabulatedFunction.extrapolateRight(10), 20, error);
        assertEquals(arrayTabulatedMathFunction.extrapolateRight(10), 1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.extrapolateRight(6), 10, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.extrapolateRight(10), 1, error);
    }

    @Test
    public void testInterpolate() {
        assertEquals(arrayTabulatedFunction.interpolate(1.5, arrayTabulatedFunction.floorIndexOfX(1.5)), 3, error);
        assertEquals(arrayTabulatedFunction.interpolate(-5, arrayTabulatedFunction.floorIndexOfX(-5)), -10, error);
        assertEquals(arrayTabulatedFunction.interpolate(10, arrayTabulatedFunction.floorIndexOfX(10)), 20, error);
        assertEquals(arrayTabulatedMathFunction.interpolate(2.2, arrayTabulatedMathFunction.floorIndexOfX(2.2)), 0.22, error);
        assertEquals(arrayTabulatedMathFunction.interpolate(-1, arrayTabulatedMathFunction.floorIndexOfX(-1)), -0.1, error);
        assertEquals(arrayTabulatedMathFunction.interpolate(10, arrayTabulatedMathFunction.floorIndexOfX(10)), 1, error);
        assertEquals(arrayTabulatedUnitArrayFunction.interpolate(4, 0), 10, error);
        assertEquals(arrayTabulatedUnitArrayFunction.interpolate(6, 0), 10, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.interpolate(2.2, arrayTabulatedMathChangeFromToFunction.floorIndexOfX(2.2)), 0.22, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.interpolate(-1, arrayTabulatedMathChangeFromToFunction.floorIndexOfX(-1)), -0.1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction.interpolate(10, arrayTabulatedMathChangeFromToFunction.floorIndexOfX(10)), 1, error);
    }
}