package ru.ssau.tk.kaf.kudrinandfirsov.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.ArrayTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.Point;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class SynchronizedTabulatedFunctionTest {
    private final double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
    private final double[] yValues = new double[]{10, 20, 30, 40, 50, 60, 70};
    private final Object object = new Object();
    private final double DELTA = 0.00001;

    private SynchronizedTabulatedFunction getSynchronizedList() {
        return new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);
    }

    private SynchronizedTabulatedFunction getSynchronizedArray() {
        return new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), object);
    }

    @Test
    public void testGetCount() {
        assertEquals(getSynchronizedList().getCount(), 7, DELTA);

        assertEquals(getSynchronizedArray().getCount(), 7, DELTA);
    }

    @Test
    public void testGetX() {
        assertEquals(getSynchronizedList().getX(0), 1, DELTA);
        assertEquals(getSynchronizedList().getX(2), 3, DELTA);
        assertEquals(getSynchronizedList().getX(5), 6, DELTA);

        assertEquals(getSynchronizedArray().getX(1), 2, DELTA);
        assertEquals(getSynchronizedArray().getX(3), 4, DELTA);
        assertEquals(getSynchronizedArray().getX(6), 7, DELTA);
    }

    @Test
    public void testGetY() {
        assertEquals(getSynchronizedList().getY(0), 10, DELTA);
        assertEquals(getSynchronizedList().getY(2), 30, DELTA);
        assertEquals(getSynchronizedList().getY(5), 60, DELTA);

        assertEquals(getSynchronizedArray().getY(1), 20, DELTA);
        assertEquals(getSynchronizedArray().getY(3), 40, DELTA);
        assertEquals(getSynchronizedArray().getY(6), 70, DELTA);
    }

    @Test
    public void testIteratorWhile() {
        Iterator<Point> testIterator = getSynchronizedList().iterator();

        int i = 0;
        while (testIterator.hasNext()) {
            Point myPoint = testIterator.next();
            assertEquals(getSynchronizedList().getX(i), myPoint.x);
            assertEquals(getSynchronizedList().getY(i++), myPoint.y);
        }
        assertEquals(getSynchronizedList().getCount(), i);

        assertThrows(NoSuchElementException.class, testIterator::next);
    }

    @Test
    public void testIteratorForEach() {
        int i = 0;
        for (Point point : getSynchronizedArray()) {
            assertEquals(point.x, getSynchronizedArray().getX(i), DELTA);
            assertEquals(point.y, getSynchronizedArray().getY(i++), DELTA);
        }
        assertEquals(getSynchronizedArray().getCount(), i);
    }

    @Test
    public void testSetY() {
        final double[] xValues = new double[]{1, 2, 3, 4, 5};
        final double[] yValues = new double[]{6, 7, 8, 9, 10};
        SynchronizedTabulatedFunction synchronizedList = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(xValues, yValues), object);
        SynchronizedTabulatedFunction synchronizedArray = new SynchronizedTabulatedFunction(new ArrayTabulatedFunction(xValues, yValues), object);

        synchronizedList.setY(0, 11);
        assertEquals(synchronizedList.getY(0), 11, DELTA);
        synchronizedList.setY(2, 44);
        assertEquals(synchronizedList.getY(2), 44, DELTA);
        synchronizedList.setY(4, 77);
        assertEquals(synchronizedList.getY(4), 77, DELTA);

        synchronizedArray.setY(0, 33);
        assertEquals(synchronizedArray.getY(0), 33, DELTA);
        synchronizedArray.setY(2, 55);
        assertEquals(synchronizedArray.getY(2), 55, DELTA);
        synchronizedArray.setY(4, 66);
        assertEquals(synchronizedArray.getY(4), 66, DELTA);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(getSynchronizedList().indexOfX(1), 0, DELTA);
        assertEquals(getSynchronizedList().indexOfX(3), 2, DELTA);
        assertEquals(getSynchronizedList().indexOfX(6), 5, DELTA);

        assertEquals(getSynchronizedArray().indexOfX(2), 1, DELTA);
        assertEquals(getSynchronizedArray().indexOfX(4), 3, DELTA);
        assertEquals(getSynchronizedArray().indexOfX(7), 6, DELTA);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(getSynchronizedList().indexOfY(10), 0, DELTA);
        assertEquals(getSynchronizedList().indexOfY(30), 2, DELTA);
        assertEquals(getSynchronizedList().indexOfY(60), 5, DELTA);

        assertEquals(getSynchronizedArray().indexOfY(20), 1, DELTA);
        assertEquals(getSynchronizedArray().indexOfY(40), 3, DELTA);
        assertEquals(getSynchronizedArray().indexOfY(70), 6, DELTA);
    }

    @Test
    public void testLeftBound() {
        assertEquals(getSynchronizedList().leftBound(), 1, DELTA);

        assertEquals(getSynchronizedArray().leftBound(), 1, DELTA);
    }

    @Test
    public void testRightBound() {
        assertEquals(getSynchronizedList().rightBound(), 7, DELTA);

        assertEquals(getSynchronizedArray().rightBound(), 7, DELTA);
    }

    @Test
    public void testApply() {
        assertEquals(getSynchronizedList().apply(3), 30, DELTA);
        assertEquals(getSynchronizedList().apply(-7), -70, DELTA);
        assertEquals(getSynchronizedList().apply(0), 0, DELTA);

        assertEquals(getSynchronizedArray().apply(0), 0, DELTA);
        assertEquals(getSynchronizedArray().apply(8), 80, DELTA);
        assertEquals(getSynchronizedArray().apply(-4), -40, DELTA);
    }
}