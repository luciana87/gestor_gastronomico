package org.ada.gestorgastronomico.exceptions;

public class EmptyFiledException extends RuntimeException{
    public static final String MESSAGE = "El campo es requerido";

    public EmptyFiledException() {
        super(MESSAGE);
    }

    public EmptyFiledException(String field) {
        super("El campo " + field + " es requerido.");
    }
}
