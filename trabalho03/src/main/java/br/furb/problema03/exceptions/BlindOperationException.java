package br.furb.problema03.exceptions;

public class BlindOperationException extends RuntimeException {
    public BlindOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
