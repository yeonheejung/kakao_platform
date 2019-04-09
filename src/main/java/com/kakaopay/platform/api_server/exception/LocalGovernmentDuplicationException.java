package com.kakaopay.platform.api_server.exception;

public class LocalGovernmentDuplicationException extends RuntimeException {

    /**
     * Constructs a {@code LocalGovernmentDuplicationException} with no detail message.
     */
    public LocalGovernmentDuplicationException() {
        super();
    }

    /**
     * Constructs a {@code LocalGovernmentDuplicationException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public LocalGovernmentDuplicationException(String s) {
        super(s);
    }
}
