package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Serializable {

    private static final long serialVersionUID = -925425195217095725L;
    private final double[] xValues;
    private final double[] yValues;
    protected int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException("недопустимый размер(меньше двух)");
        }
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("недопустимый размер(меньше двух)");
        }
        if (xFrom > xTo) {
            throw new IllegalArgumentException("xFrom > xTo");
        }
        this.count = count;
        xValues = new double[count];
        yValues = new double[count];
        double step = (xTo - xFrom) / (count - 1);
        xValues[0] = xFrom;
        yValues[0] = source.apply(xFrom);
        for (int i = 1; i < count - 1; i++) {
            xFrom += step;
            xValues[i] = xFrom;
            yValues[i] = source.apply(xFrom);
        }
        xValues[count - 1] = xTo;
        yValues[count - 1] = source.apply(xTo);
    }

    @Override
    public int getCount() {
        return (count);
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < leftBound()) {
            throw new IllegalArgumentException("x меньше левой границы");
        }

        if (x > rightBound()) {
            return count - 1;
        }
        for (int i = 0; i != count; i++) {
            if ((x > xValues[i]) && (x < xValues[i + 1])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    public double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    public double interpolate(double x, int floorIndexOfX) {
        if (floorIndexOfX == 0) {
            return extrapolateLeft(x);
        }
        if (floorIndexOfX == count - 1) {
            return extrapolateRight(x);
        }
        if ((x < xValues[floorIndexOfX]) || (x > xValues[floorIndexOfX + 1])) {
            throw new InterpolationException("x не в рамках интерполяции");
        }
        return interpolate(x, xValues[floorIndexOfX], xValues[floorIndexOfX + 1], yValues[floorIndexOfX], yValues[floorIndexOfX + 1]);
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }
}
