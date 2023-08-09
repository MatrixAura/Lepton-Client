package cn.matrixaura.lepton.exception;

public class SystemNotSupportedException extends RuntimeException {

    public SystemNotSupportedException() {
        super();
    }

    public SystemNotSupportedException(String message) {
        super(message);
    }

    public SystemNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemNotSupportedException(Throwable cause) {
        super(cause);
    }
}
