package by.smirnov.chequeprintproject.printer;

public class ConsoleChequePrinter extends ChequePrinter{

    @Override
    public void print(CharSequence cheque) {

        System.out.println(cheque);
    }
}
