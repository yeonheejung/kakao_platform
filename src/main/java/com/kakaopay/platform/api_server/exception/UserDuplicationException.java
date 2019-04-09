package com.kakaopay.platform.api_server.exception;

public class UserDuplicationException extends RuntimeException {
    /**
     * Constructs a {@code UserDuplicationException} with no detail message.
     */
    public UserDuplicationException() {
        super();
    }

    /**
     * Constructs a {@code UserDuplicationException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public UserDuplicationException(String s) {
        super(s);
    }
}
