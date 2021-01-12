package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import org.testng.annotations.Test;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.ArrayIsNotSortedException;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.DifferentLengthOfArraysException;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InterpolationException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {

    final double[] xValues = new double[]{-2, -1, 0, 1, 2};
    final double[] yValues = new double[]{4, 1, 0, 1, 4};
    LinkedListTabulatedFunction linkedListTabulatedFunction1 = new LinkedListTabulatedFunction(xValues, yValues);
    CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new TenthFunction());
    LinkedListTabulatedFunction linkedListTabulatedFunction3 = new LinkedListTabulatedFunction(compositeFunction, -3, 3, 7);
    LinkedListTabulatedFunction linkedListTabulatedFunction4;
    LinkedListTabulatedFunction linkedListTabulatedFunction5;

    public LinkedListTabulatedFunction linkedListTabulatedFunction4() {
        final double[] xValues = new double[]{-2, -1, 0, 1, 2};
        final double[] yValues = new double[]{4, 1, 0, 1, 4};
        return linkedListTabulatedFunction4 = new LinkedListTabulatedFunction(xValues, yValues);
    }

    public LinkedListTabulatedFunction linkedListTabulatedFunction5() {
        final CompositeFunction compositeFunction = new CompositeFunction(new SqrFunction(), new TenthFunction());
        return linkedListTabulatedFunction5 = new LinkedListTabulatedFunction(compositeFunction, -3, 3, 7);
    }

    @Test
    public void testCount() {
        assertEquals(linkedListTabulatedFunction1.getCount(), 5, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getCount(), 7, 0.00001);
    }

    @Test
    public void testGetX() {
        assertEquals(linkedListTabulatedFunction1.getX(0), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getX(4), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getX(3), 0, 0.00001);
    }

    @Test
    public void testGetY() {
        assertEquals(linkedListTabulatedFunction1.getY(0), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getY(4), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getY(4), 0.1, 0.00001);
    }

    @Test
    public void testSetY() {
        linkedListTabulatedFunction1.setY(0, 16);
        linkedListTabulatedFunction1.setY(4, 18);
        linkedListTabulatedFunction3.setY(3, 16);
        assertEquals(linkedListTabulatedFunction1.getY(0), 16, 0.00001);
        assertEquals(linkedListTabulatedFunction1.getY(4), 18, 0.00001);
        assertEquals(linkedListTabulatedFunction3.getY(3), 16, 0.00001);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(linkedListTabulatedFunction1.indexOfX(-1), 1, 0.00001);
        assertEquals(linkedListTabulatedFunction1.indexOfX(10), -1, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfX(-1), 2, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfX(-5), -1, 0.00001);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(linkedListTabulatedFunction1.indexOfY(4), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3.indexOfY(100), -1, 0.00001);
    }

    @Test
    public void testLeftBound() {
        assertEquals(linkedListTabulatedFunction1.leftBound(), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction3.leftBound(), -3, 0.00001);
    }

    @Test
    public void testRightBound() {
        assertEquals(linkedListTabulatedFunction1.rightBound(), -2, 0.00001);
        assertEquals(linkedListTabulatedFunction3.rightBound(), 3, 0.00001);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(linkedListTabulatedFunction1.floorIndexOfX(-1), 0, 0.00001);
        assertEquals(linkedListTabulatedFunction3.floorIndexOfX(1.5), 4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.floorIndexOfX(-2.5), 0, 0.00001);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(linkedListTabulatedFunction1.extrapolateLeft(-3), 7, 0.00001);
        assertEquals(linkedListTabulatedFunction1.extrapolateLeft(-5), 13, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateLeft(-5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateLeft(-10), 4.4, 0.00001);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(linkedListTabulatedFunction1.extrapolateRight(3), 7, 0.00001);
        assertEquals(linkedListTabulatedFunction1.extrapolateRight(5), 13, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(10), 4.4, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(5), 1.9, 0.00001);
        assertEquals(linkedListTabulatedFunction3.extrapolateRight(10), 4.4, 0.00001);
    }

    @Test
    public void testInterpolate() {
        assertEquals(linkedListTabulatedFunction1.interpolate(16, 2), 16, 0.00001);
        assertEquals(linkedListTabulatedFunction3.interpolate(15, 3), 1.5, 0.00001);
        assertEquals(linkedListTabulatedFunction3.interpolate(-20, 5), -10.6, 0.00001);
    }

    @Test
    public void testApply() {
        assertEquals(linkedListTabulatedFunction1.apply(1.), 23., 0.00001);
        assertEquals(linkedListTabulatedFunction1.apply(2.), 4., 0.00001);
        assertEquals(linkedListTabulatedFunction1.apply(2.5), 6.5, 0.00001);
        assertEquals(linkedListTabulatedFunction3.apply(-7.), -7., 0.00001);
        assertEquals(linkedListTabulatedFunction3.apply(9.), 51., 0.00001);
        assertEquals(linkedListTabulatedFunction3.apply(4.), 16., 0.00001);
        assertEquals(linkedListTabulatedFunction3.apply(1.2), 1.6, 0.00001);
    }

    @Test
    public void testInterpolationException() {
        assertThrows(InterpolationException.class, () -> {
            linkedListTabulatedFunction4().interpolate(-2, 1);
            linkedListTabulatedFunction5().interpolate(-1, 5);
            linkedListTabulatedFunction5().interpolate(-2, 3);
        });
    }

    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4});
            new LinkedListTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 5});
            new LinkedListTabulatedFunction(new double[]{1}, new double[]{});
        });
    }

    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            new LinkedListTabulatedFunction(new double[]{5, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            new LinkedListTabulatedFunction(new double[]{1, 5, 3, 4}, new double[]{1, 2, 3, 4});
            new LinkedListTabulatedFunction(new double[]{3, 2}, new double[]{1, 2});
        });
    }
}