package com.n26.test.mvitolo.exception;

/**
 * Created by Marco on 09/01/2018.
 */
public class ElaborateStatisticException extends Exception {

    private static final long serialVersionUID = -6612504836482459927L;

    public ElaborateStatisticException() {
        super();
    }

    public ElaborateStatisticException(String message) {
        super(message);
    }

    public ElaborateStatisticException(String message, Throwable cause) {
        super(message, cause);
    }

    public ElaborateStatisticException(Throwable cause) {
        super(cause);
    }

}
