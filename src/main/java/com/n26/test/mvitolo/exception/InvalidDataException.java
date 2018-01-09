package com.n26.test.mvitolo.exception;

/**
 * Created by Marco on 09/01/2018.
 */
public class InvalidDataException extends Exception {

    private static final long serialVersionUID = -6617304836482459928L;

    public InvalidDataException() {
        super();
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidDataException(Throwable cause) {
        super(cause);
    }

}
