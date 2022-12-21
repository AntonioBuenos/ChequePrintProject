package by.smirnov.chequeprintproject.printer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static by.smirnov.chequeprintproject.service.chequebuilder.ChequeConstants.REPORT_FILENAME;
import static by.smirnov.chequeprintproject.util.PathGetter.getPath;

public class FileChequePrinter extends ChequePrinter {
    @Override
    public void print(CharSequence cheque) {
        writeToFile(cheque);
    }

    private void writeToFile(CharSequence cheque) {
        String path = getPath(REPORT_FILENAME);
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(cheque);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
