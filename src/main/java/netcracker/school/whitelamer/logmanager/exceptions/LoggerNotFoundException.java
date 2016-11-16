package netcracker.school.whitelamer.logmanager.exceptions;

public class LoggerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoggerNotFoundException(String message) {
        super(message);
    }
}
