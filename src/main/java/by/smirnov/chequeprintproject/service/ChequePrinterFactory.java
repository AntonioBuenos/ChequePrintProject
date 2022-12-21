package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.printer.ChequePrinter;
import by.smirnov.chequeprintproject.printer.ConsoleChequePrinter;
import by.smirnov.chequeprintproject.printer.FileChequePrinter;

public class ChequePrinterFactory {

    public ChequePrinter createPrinter(PrinterType printerType){
        if(printerType.equals(PrinterType.FILE)) return new FileChequePrinter();
        else return new ConsoleChequePrinter();
    }

    public enum PrinterType{
        FILE, CONSOLE
    }
}
