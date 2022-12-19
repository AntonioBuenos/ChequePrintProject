package by.smirnov.chequeprintproject.parser;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.repository.DiscountCardRepository;
import by.smirnov.chequeprintproject.repository.ProductRepository;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.service.chequebuilder.StringChequeBuilder;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class InputHandler {

    private final ProductRepository repository;
    private final DiscountCardRepository cardRepository;

    public void handleCheque(String input){
        handleCheque(input.split(" "));
    }

    public void handleCheque(String[] params){
        Map<Product, Integer> productCart = new HashMap<>();
        for (int i = 2; i < params.length - 2; i++) {
            String[] productLine = params[i].split("-");
            productCart.put(repository.findById(Long.parseLong(productLine[0])), Integer.parseInt(productLine[1]));
        }
        String[] cardline = params[params.length - 2].split("-");
        DiscountCard card = cardRepository.findById(Long.parseLong(cardline[1]));
        String[] cashierline = params[params.length - 1].split("-");
        Cashier cashier = new Cashier(Long.parseLong(cashierline[1]), Store.SHOP);
        ChequeCounter chequeCounter = new ChequeCounter(productCart, card);
        StringChequeBuilder chequeBuilder = new StringChequeBuilder(chequeCounter);
        chequeBuilder.print(cashier);
    }
}
