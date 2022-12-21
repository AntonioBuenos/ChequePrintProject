package by.smirnov.chequeprintproject.service.handler;

import by.smirnov.chequeprintproject.printer.ChequePrinter;
import by.smirnov.chequeprintproject.printer.FileChequePrinter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChequePrinterFactoryTest {



    @Test
    void createPrinterTest() {
        ChequePrinterFactory chequePrinterFactory = new ChequePrinterFactory();
        ChequePrinter chequePrinter = chequePrinterFactory.createPrinter(ChequePrinterFactory.PrinterType.FILE);
        assertTrue(chequePrinter instanceof FileChequePrinter);
    }
}
