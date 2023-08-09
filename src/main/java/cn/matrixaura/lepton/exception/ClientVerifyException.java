package cn.matrixaura.lepton.exception;

public class ClientVerifyException extends RuntimeException {
    public ClientVerifyException() {
        super();
    }

    public ClientVerifyException(String message) {
        super(message);
    }

    public ClientVerifyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientVerifyException(Throwable cause) {
        super(cause);
    }
}
