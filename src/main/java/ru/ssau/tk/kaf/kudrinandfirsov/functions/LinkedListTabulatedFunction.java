package ru.ssau.tk.kaf.kudrinandfirsov.functions;

import ru.ssau.tk.kaf.kudrinandfirsov.exceptions.InterpolationException;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Serializable {

    private static final long serialVersionUID = 7111641501642184781L;
    private Node head;
    private int count;

    protected static class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;
    }

    private void addNode(double x, double y) {
        Node newNode = new Node();
        if (head == null) {
            newNode.x = x;
            newNode.y = y;
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
        } else {
            newNode.x = x;
            newNode.y = y;
            Node last = head.prev;
            last.next = newNode;
            head.prev = newNode;
            newNode.prev = last;
            newNode.next = head;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        if (xValues.length < 2) {
            throw new IllegalArgumentException("недопустимый размер узла(меньше двух)");
        }
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("недопустимый размер узла(меньше двух)");
        }
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    public Node getNode(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("индекс за границами");
        }
        Node iNode;
        if (index < count / 2) {
            iNode = head;
            for (int i = 0; i <= count / 2; i++) {
                if (i == index) {
                    return iNode;
                } else {
                    iNode = iNode.next;
                }
            }
        } else {
            iNode = head.prev;
            for (int i = count - 1; i >= count / 2; i--) {
                if (i == index) {
                    return iNode;
                } else {
                    iNode = iNode.prev;
                }
            }
        }
        return iNode;
    }

    protected Node floorNodeOfX(double x) {
        Node node = head;
        if (x < head.x) {
            throw new IllegalArgumentException("х меньше минимально х из листа");
        }
        for (int i = 0; i < count; i++) {
            if (node.x < x) {
                node = node.next;
            } else {
                return node.prev;
            }
        }
        return head.prev;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    @Override
    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public int indexOfX(double x) {
        Node xNode = head;
        for (int i = 0; i < count; i++) {
            if (xNode.x == x) {
                return i;
            }
            xNode = xNode.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node yNode = head;
        for (int i = 0; i < count; i++) {
            if (yNode.y == y) {
                return i;
            }
            yNode = yNode.next;
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x) {
        if (x < head.x) {
            throw new IllegalArgumentException("х меньше минимального х из листа");
        }
        Node node = head;
        for (int i = 0; i <= count; i++) {
            if (node.x < x) {
                node = node.next;
            } else {
                return i - 1;
            }
        }
        return getCount();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndexOfX) {
        if (x < getNode(floorIndexOfX).x || x > getNode(floorIndexOfX + 1).x) {
            throw new InterpolationException("x не в рамках интерполяции");
        }
        Node left = getNode(floorIndexOfX);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    protected double interpolate(double x, Node floorNodeOfX) {
        if (head.x == head.prev.x) {
            return x;
        }
        Node left = floorNodeOfX;
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorNodeOfX(x));
        }
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            Node node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    Point point = new Point(node.x, node.y);
                    if (node == head.prev) {
                        node = null;
                    } else {
                        node = node.next;
                    }
                    return point;
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}