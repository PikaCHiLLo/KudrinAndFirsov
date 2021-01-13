package ru.ssau.tk.kaf.kudrinandfirsov.io;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.Point;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.TabulatedFunction;

import java.io.*;

final public class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        PrintWriter printWriter = new PrintWriter(writer);
        printWriter.println(function.getCount());
        for (Point temp : function) {
            printWriter.printf("%f %f\n", temp.x, temp.y);
        }
        printWriter.flush();
    }
}