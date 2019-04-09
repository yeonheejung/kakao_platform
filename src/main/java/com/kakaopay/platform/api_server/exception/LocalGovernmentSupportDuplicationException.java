package com.kakaopay.platform.api_server.exception;

public class LocalGovernmentSupportDuplicationException extends RuntimeException {

    /**
     * Constructs a {@code LocalGovernmentSupportDuplicationException} with no detail message.
     */
    public LocalGovernmentSupportDuplicationException() {
        super();
    }

    /**
     * Constructs a {@code LocalGovernmentSupportDuplicationException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public LocalGovernmentSupportDuplicationException(String s) {
        super(s);
    }
}
