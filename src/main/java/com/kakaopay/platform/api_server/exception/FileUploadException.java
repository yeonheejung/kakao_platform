package com.kakaopay.platform.api_server.exception;

public class FileUploadException extends RuntimeException {
    /**
     * Constructs a {@code FileUploadException} with no detail message.
     */
    public FileUploadException() {
        super();
    }

    /**
     * Constructs a {@code FileUploadException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public FileUploadException(String s) {
        super(s);
    }
}
