package by.smirnov.chequeprintproject.exceptionhandler;

public class BadRequestException extends RuntimeException {

    public BadRequestException () {
        super("SMTH");
    }

    @Override
    public String toString() {
        return "BadRequestException{}" + super.toString();
    }
}
