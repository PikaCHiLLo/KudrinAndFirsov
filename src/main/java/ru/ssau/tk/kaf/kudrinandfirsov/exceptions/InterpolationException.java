package ru.ssau.tk.kaf.kudrinandfirsov.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 667162581669708308L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
