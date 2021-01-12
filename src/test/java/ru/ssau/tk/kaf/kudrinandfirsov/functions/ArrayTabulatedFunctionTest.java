package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InterpolationException;
import java.util.NoSuchElementException;
import java.util.Iterator;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {

    private final static double error = 0.0005;

    private final MathFunction tenthFunction = new TenthFunction();

    public ArrayTabulatedFunction arrayTabulatedFunction() {
        final double[] xValues = new double[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        final double[] yValues = new double[]{-6, -4, -2, 0, 2, 4, 6, 8, 10};
        return new ArrayTabulatedFunction(xValues, yValues);
    }

    public ArrayTabulatedFunction arrayTabulatedMathFunction() {
        return new ArrayTabulatedFunction(tenthFunction, 1, 9, 17);
    }

    public ArrayTabulatedFunction arrayTabulatedMathChangeFromToFunction() {
        return new ArrayTabulatedFunction(tenthFunction, 9, 1, 17);
    }

    @Test
    public void testGetCount() {
        assertEquals(arrayTabulatedFunction().getCount(), 9);
        assertEquals(arrayTabulatedMathFunction().getCount(), 17);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getCount(), 17);
    }

    @Test
    public void testGetX() {
        assertEquals(arrayTabulatedFunction().getX(0), -3, error);
        assertEquals(arrayTabulatedFunction().getX(2), -1, error);
        assertEquals(arrayTabulatedFunction().getX(4), 1, error);
        assertEquals(arrayTabulatedMathFunction().getX(0), 1, error);
        assertEquals(arrayTabulatedMathFunction().getX(2), 2, error);
        assertEquals(arrayTabulatedMathFunction().getX(4), 3, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getX(0), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getX(2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getX(4), 3, error);
    }

    @Test
    public void testGetY() {
        assertEquals(arrayTabulatedFunction().getY(0), -6, error);
        assertEquals(arrayTabulatedFunction().getY(2), -2, error);
        assertEquals(arrayTabulatedFunction().getY(4), 2, error);
        assertEquals(arrayTabulatedMathFunction().getY(0), 0.1, error);
        assertEquals(arrayTabulatedMathFunction().getY(2), 0.2, error);
        assertEquals(arrayTabulatedMathFunction().getY(4), 0.3, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getY(0), 0.1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getY(2), 0.2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().getY(4), 0.3, error);
    }

    @Test
    public void testSetY() {
        final double[] xValues = new double[]{-3, -2, -1, 0, 1, 2, 3, 4, 5};
        final double[] yValues = new double[]{-6, -4, -2, 0, 2, 4, 6, 8, 10};
        ArrayTabulatedFunction array1 = new ArrayTabulatedFunction(xValues, yValues);
        ArrayTabulatedFunction array2 = new ArrayTabulatedFunction(tenthFunction, 1, 9, 17);
        ArrayTabulatedFunction array3 = new ArrayTabulatedFunction(tenthFunction, 9, 1, 17);
        array1.setY(0, -6);
        assertEquals(array1.getY(0), -6, error);
        array2.setY(1, -6);
        assertEquals(array2.getY(1), -6, error);
        array3.setY(1, -6);
        assertEquals(array3.getY(1), -6, error);
    }

    @Test
    public void testLeftBound() {
        assertEquals(arrayTabulatedFunction().leftBound(), -3, error);
        assertEquals(arrayTabulatedMathFunction().leftBound(), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().leftBound(), 1, error);
    }

    @Test
    public void testRightBound() {
        assertEquals(arrayTabulatedFunction().rightBound(), 5, error);
        assertEquals(arrayTabulatedMathFunction().rightBound(), 9, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().rightBound(), 9, error);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(arrayTabulatedFunction().indexOfX(0), 3, error);
        assertEquals(arrayTabulatedFunction().indexOfX(3), 6, error);
        assertEquals(arrayTabulatedFunction().indexOfX(7), -1, error);
        assertEquals(arrayTabulatedMathFunction().indexOfX(2), 2, error);
        assertEquals(arrayTabulatedMathFunction().indexOfX(4), 6, error);
        assertEquals(arrayTabulatedMathFunction().indexOfX(20), -1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfX(2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfX(4), 6, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfX(20), -1, error);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(arrayTabulatedFunction().indexOfY(0), 3, error);
        assertEquals(arrayTabulatedFunction().indexOfY(6), 6, error);
        assertEquals(arrayTabulatedFunction().indexOfY(14), -1, error);
        assertEquals(arrayTabulatedMathFunction().indexOfY(0.2), 2, error);
        assertEquals(arrayTabulatedMathFunction().indexOfY(0.4), 6, error);
        assertEquals(arrayTabulatedMathFunction().indexOfY(2), -1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfY(0.2), 2, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfY(0.4), 6, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().indexOfY(2), -1, error);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(arrayTabulatedFunction().floorIndexOfX(0.1), 3, error);
        assertEquals(arrayTabulatedFunction().floorIndexOfX(3.5), 6, error);
        assertEquals(arrayTabulatedFunction().floorIndexOfX(7), 8, error);
        assertEquals(arrayTabulatedFunction().floorIndexOfX(-2.5), 0, error);
        assertEquals(arrayTabulatedMathFunction().floorIndexOfX(1.6), 1, error);
        assertEquals(arrayTabulatedMathFunction().floorIndexOfX(3.8), 5, error);
        assertEquals(arrayTabulatedMathFunction().floorIndexOfX(10), 16, error);
        assertEquals(arrayTabulatedMathFunction().floorIndexOfX(1.1), 0, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().floorIndexOfX(1.6), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().floorIndexOfX(3.8), 5, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().floorIndexOfX(10), 16, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().floorIndexOfX(1.1), 0, error);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(arrayTabulatedFunction().extrapolateLeft(-4), -8, error);
        assertEquals(arrayTabulatedMathFunction().extrapolateLeft(-1), -0.1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().extrapolateLeft(-1), -0.1, error);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(arrayTabulatedFunction().extrapolateRight(10), 20, error);
        assertEquals(arrayTabulatedMathFunction().extrapolateRight(10), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().extrapolateRight(10), 1, error);
    }

    @Test
    public void testInterpolate() {
        assertEquals(arrayTabulatedFunction().interpolate(1.5, arrayTabulatedFunction().floorIndexOfX(1.5)), 3, error);
        assertEquals(arrayTabulatedMathFunction().interpolate(10, arrayTabulatedMathFunction().floorIndexOfX(10)), 1, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().interpolate(2.2, arrayTabulatedMathChangeFromToFunction().floorIndexOfX(2.2)), 0.22, error);
        assertEquals(arrayTabulatedMathChangeFromToFunction().interpolate(10, arrayTabulatedMathChangeFromToFunction().floorIndexOfX(10)), 1, error);
    }

    @Test
    public void testInterpolationException() {
        assertThrows(InterpolationException.class, () -> {
            arrayTabulatedFunction().interpolate(-2, 1);
            arrayTabulatedMathFunction().interpolate(-1, 5);
            arrayTabulatedMathFunction().interpolate(-2, 3);
            arrayTabulatedMathChangeFromToFunction().interpolate(-1, 5);
            arrayTabulatedMathChangeFromToFunction().interpolate(-2, 3);
        });
    }
    @Test
    public void testCheckLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            new ArrayTabulatedFunction(new double[]{1, 2, 3, 4, 5}, new double[]{1, 2, 3, 4});
            new ArrayTabulatedFunction(new double[]{1, 2, 3, 4}, new double[]{1, 2, 3, 4, 5});
            new ArrayTabulatedFunction(new double[]{1}, new double[]{});
        });
    }
    @Test
    public void testCheckSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            new ArrayTabulatedFunction(new double[]{5, 2, 3, 4, 5}, new double[]{1, 2, 3, 4, 5});
            new ArrayTabulatedFunction(new double[]{1, 5, 3, 4}, new double[]{1, 2, 3, 4});
            new ArrayTabulatedFunction(new double[]{3, 2}, new double[]{1, 2});
        });
    }
    @Test
    public void testIterator() {
        Iterator<Point> iterator = arrayTabulatedFunction().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(point.x, arrayTabulatedFunction().getX(i++));
        }
        assertThrows(NoSuchElementException.class, () -> {
            Point point = iterator.next();
        });
        i = 0;
        for (Point point : arrayTabulatedFunction()) {
            assertEquals(point.x, arrayTabulatedFunction().getX(i++));
        }
    }
}