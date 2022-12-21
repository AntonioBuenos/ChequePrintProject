package by.smirnov.chequeprintproject.exceptionhandler;

import static by.smirnov.chequeprintproject.exceptionhandler.ExceptionConstants.NOT_FOUND_MESSAGE;

public class NoSuchEntityException extends RuntimeException {


    public NoSuchEntityException(String message) {
        super(message);
    }
    public NoSuchEntityException() {
        super(NOT_FOUND_MESSAGE);
    }

    @Override
    public String toString() {
        return "NoSuchEntityException{}" + super.toString();
    }
}
