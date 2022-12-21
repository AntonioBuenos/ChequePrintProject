package by.smirnov.chequeprintproject.service.handler;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.printer.ChequePrinter;
import by.smirnov.chequeprintproject.repository.DiscountCardRepository;
import by.smirnov.chequeprintproject.repository.DiscountCardSetRepositoryImpl;
import by.smirnov.chequeprintproject.repository.ProductRepository;
import by.smirnov.chequeprintproject.repository.ProductSetRepositoryImpl;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.service.chequebuilder.StringChequeBuilder;
import by.smirnov.chequeprintproject.util.InputFileReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputHandler {

    private final ProductRepository repository;
    private final DiscountCardRepository cardRepository;
    private final ChequePrinterFactory factory = new ChequePrinterFactory();

    public InputHandler() {
        repository = new ProductSetRepositoryImpl();
        cardRepository = new DiscountCardSetRepositoryImpl();
    }

    public void processCheque(String[] args) {
        if (args[0].equalsIgnoreCase(InputHandlerConstants.KEY_START)) {
            if (args[1].equalsIgnoreCase(InputHandlerConstants.KEY_ARGS)) {
                handleCheque(args);
            } else if (args[1].equalsIgnoreCase(InputHandlerConstants.KEY_FILE)) {
                List<String> lines = InputFileReader.readFile(args[2]);
                for (String line : lines) {
                    handleCheque(line);
                }
            }
        }
    }

    private void handleCheque(String input) {
        handleCheque(input.split(InputHandlerConstants.DELIM_SPACE));
    }

    private void handleCheque(String[] params) {
        DiscountCard card = cardRepository.findById(getValue(params[params.length - 2]));
        Cashier cashier = new Cashier(getValue(params[params.length - 1]), Store.SHOP);
        StringChequeBuilder chequeBuilder = new StringChequeBuilder(
                new ChequeCounter(getProductCart(params), card));
        StringBuilder cheque = chequeBuilder.buildCheque(cashier);
        ChequePrinter printer = factory.createPrinter(ChequePrinterFactory.PrinterType.FILE);
        printer.print(cheque);
        ChequePrinter printerCons = factory.createPrinter(ChequePrinterFactory.PrinterType.CONSOLE);
        printerCons.print(cheque);
    }

    private Map<Product, Integer> getProductCart(String[] params) {
        Map<Product, Integer> productCart = new HashMap<>();
        for (int i = 2; i < params.length - 2; i++) {
            String[] productLine = params[i].split(InputHandlerConstants.DELIM_DASH);
            productCart.put(repository.findById(Long.parseLong(productLine[0])), Integer.parseInt(productLine[1]));
        }
        return productCart;
    }

    private Long getValue(String param) {
        String[] keyAdValue = param.split(InputHandlerConstants.DELIM_DASH);
        return Long.parseLong(keyAdValue[1]);
    }

}
