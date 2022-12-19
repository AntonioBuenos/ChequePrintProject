package by.smirnov.chequeprintproject;

import by.smirnov.chequeprintproject.parser.InputFileReader;
import by.smirnov.chequeprintproject.parser.InputHandler;
import by.smirnov.chequeprintproject.repository.DiscountCardSetRepositoryImpl;
import by.smirnov.chequeprintproject.repository.ProductSetRepositoryImpl;

import java.util.List;

public class ChequeRunner {

    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("java")) {
            InputHandler inputHandler = new InputHandler(
                    new ProductSetRepositoryImpl(),
                    new DiscountCardSetRepositoryImpl());
            if (args[1].equalsIgnoreCase("CheckRunner")) {
                inputHandler.handleCheque(args);
            } else if (args[1].equalsIgnoreCase("file")) {
                List<String> lines = InputFileReader.readFile(args[2]);
                for (String line : lines) {
                    inputHandler.handleCheque(line);
                }
            }
        }
    }
}
