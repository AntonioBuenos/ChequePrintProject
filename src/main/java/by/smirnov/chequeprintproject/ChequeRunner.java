package by.smirnov.chequeprintproject;

import by.smirnov.chequeprintproject.service.handler.InputHandler;

public class ChequeRunner {

    public static void main(String[] args) {
        new InputHandler().processCheque(args);
    }
}
