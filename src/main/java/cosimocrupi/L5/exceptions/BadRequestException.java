package cosimocrupi.L5.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String mess) {
        super(mess);
    }
}
