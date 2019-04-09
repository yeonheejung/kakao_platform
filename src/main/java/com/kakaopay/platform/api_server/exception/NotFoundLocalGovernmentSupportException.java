package com.kakaopay.platform.api_server.exception;

public class NotFoundLocalGovernmentSupportException extends RuntimeException {

    /**
     * Constructs a {@code NotFoundLocalGovernmentSupportException} with no detail message.
     */
    public NotFoundLocalGovernmentSupportException() {
        super();
    }

    /**
     * Constructs a {@code NotFoundLocalGovernmentSupportException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public NotFoundLocalGovernmentSupportException(String s) {
        super(s);
    }
}
