package com.kakaopay.platform.api_server.exception;

public class NoParamsException extends RuntimeException {
    /**
     * Constructs a {@code NoParamsException} with no detail message.
     */
    public NoParamsException() {
        super();
    }

    /**
     * Constructs a {@code NoParamsException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public NoParamsException(String s) {
        super(s);
    }
}
