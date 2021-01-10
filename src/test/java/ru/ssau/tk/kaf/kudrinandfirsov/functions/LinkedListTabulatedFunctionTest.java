package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    protected final double[] xValues1 = new double[]{-2, -1, 0, 1, 2};
    protected final double[] yValues1 = new double[]{4, 1, 0, 1, 4};
    protected final double[] xValues2 = new double[]{-4, -3, -2, -1, 0, 1, 2, 3, 4};
    protected final double[] yValues2 = new double[]{16, 9, 4, 1, 0, 1, 4, 9, 16};
    LinkedListTabulatedFunction linkedListTabulatedFunction1 = new LinkedListTabulatedFunction(xValues1, yValues1);
    LinkedListTabulatedFunction linkedListTabulatedFunction2 = new LinkedListTabulatedFunction(xValues2, yValues2);
    CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new TenthFunction());
    LinkedListTabulatedFunction linkedListTabulatedFunction3 = new LinkedListTabulatedFunction(compositeFunction, -3, 3, 7);

    @Test
    public void testCount() {
        assertEquals(linkedListTabulatedFunction1.getCount(), 5, 0.00001);
        assertEquals(linkedListTabulatedFunction2.getCount(), 9, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getCount(), 7, 0.00001);
    }

    @Test
    public void testGetX() {
        assertEquals(linkedListTabulatedFunction1.getX(0), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getX(4), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction2.getX(4), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getX(3), 0, 0.00001);
    }

    @Test
    public void testGetY() {
        assertEquals(linkedListTabulatedFunction1.getY(0), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getY(4), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction2.getY(4), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getY(4), 0.1, 0.00001);
    }

    @Test
    public void testSetY() {
        linkedListTabulatedFunction1.setY(0, 16);
        linkedListTabulatedFunction1.setY(4, 18);
        linkedListTabulatedFunction2.setY(4, -20);
        linkedListTabulatedFunction3.setY(3, 16);
        assertEquals(linkedListTabulatedFunction1.getY(0), 16, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getY(4), 18, 0.00001);
        assertEquals(linkedListTabulatedFunction2.getY(4), -20, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getY(3), 16, 0.00001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(linkedListTabulatedFunction1.indexOfX(-1), 1, 0.00001);
        assertEquals(linkedListTabulatedFunction1.indexOfX(10), -1, 0.00001);
        assertEquals(linkedListTabulatedFunction2.indexOfX(3), 7, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfX(-1), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfX(-5), -1, 0.00001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(linkedListTabulatedFunction2.indexOfY(16), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction2.indexOfY(436436), -1, 0.00001);
        assertEquals(linkedListTabulatedFunction1.indexOfY(4), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfY(100), -1, 0.00001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(linkedListTabulatedFunction1.leftBound(), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction2.leftBound(), -4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.leftBound(), -3, 0.00001);
    }

    @Test
    public void testRightBound() {
        assertEquals(linkedListTabulatedFunction1.rightBound(), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction2.rightBound(), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.rightBound(), 3, 0.00001);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(linkedListTabulatedFunction1.floorIndexOfX(-1), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction2.floorIndexOfX(-1), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction2.floorIndexOfX(-0.1421), 3, 0.00001);
        assertEquals(linkedListTabulatedFunction3.floorIndexOfX(1.5), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.floorIndexOfX(-2.5), 0, 0.00001);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(linkedListTabulatedFunction1.extrapolateLeft(-3), 7, 0.00001);
        assertEquals(linkedListTabulatedFunction1.extrapolateLeft(-5), 13, 0.00001);
        assertEquals(linkedListTabulatedFunction2.extrapolateLeft(-5), 23, 0.00001);
        assertEquals(linkedListTabulatedFunction2.extrapolateLeft(-10), 58, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateLeft(-5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateLeft(-10), 4.4, 0.00001);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(linkedListTabulatedFunction1.extrapolateRight(3), 7, 0.00001);
        assertEquals(linkedListTabulatedFunction1.extrapolateRight(5), 13, 0.00001);
        assertEquals(linkedListTabulatedFunction2.extrapolateRight(5), 23, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(10), 4.4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(10), 4.4, 0.00001);
    }

    @Test
    public void testInterpolate() {
        assertEquals(linkedListTabulatedFunction1.interpolate(16, 2), 16, 0.00001);
        assertEquals(linkedListTabulatedFunction2.interpolate(20, 2), -62, 0.00001);
        assertEquals(linkedListTabulatedFunction2.interpolate(20, 6), 94, 0.00001);
        assertEquals(linkedListTabulatedFunction3.interpolate(15, 3), 1.5, 0.00001);
        assertEquals(linkedListTabulatedFunction3.interpolate(-20, 5), -10.6, 0.00001);
    }
}