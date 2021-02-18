package ru.ssau.tk.kaf.kudrinandfirsov.concurrent;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;

public class ReadWriteTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;
    public double x;
    public double y;

    ReadWriteTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            x = tabulatedFunction.getX(i);
            synchronized (tabulatedFunction) {
                y = tabulatedFunction.getY(i);
                System.out.printf("%s, before write: i =  % d  , x = %f, y = %f", Thread.currentThread().getName(), i, x, y);
                System.out.println();
                tabulatedFunction.setY(i, y + 1);
                y = tabulatedFunction.getY(i);
            }
            System.out.printf("%s, after write: i = %d, x = %f, y = %f \n", Thread.currentThread().getName(), i, x, y);
        }
    }
}