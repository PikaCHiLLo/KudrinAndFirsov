package ru.ssau.tk.kaf.kudrinandfirsov.functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {

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
        for (int i = 0; i < xValues.length; i++) {
            this.addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double step = (xTo - xFrom) / (count - 1);
        for (int i = 0; i < count; i++) {
            addNode(xFrom, source.apply(xFrom));
            xFrom += step;
        }
    }

    public Node getNode(int index) {
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
        if (node.x > x) {
            return head;
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
            return 0;
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
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (head.x == head.prev.x) {
            return head.y;
        }
        return interpolate(x, getNode(floorIndex).x, getNode(floorIndex + 1).x, getNode(floorIndex).y, getNode(floorIndex + 1).y);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            Node node = floorNodeOfX(x);
            if (node.x == x) {
                return node.y;
            } else {
                return interpolate(x, node.x, node.next.x, node.y, node.next.y);
            }
        }
    }
}