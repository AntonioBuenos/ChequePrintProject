package by.smirnov.chequeprintproject;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.service.chequebuilder.StringChequeBuilder;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.repository.DiscountCardRepository;
import by.smirnov.chequeprintproject.repository.DiscountCardSetRepositoryImpl;
import by.smirnov.chequeprintproject.repository.ProductRepository;
import by.smirnov.chequeprintproject.repository.ProductSetRepositoryImpl;

import java.util.HashMap;
import java.util.Map;

public class Runner {

    public static void main(String[] args) {
        ProductRepository repository = new ProductSetRepositoryImpl();
        DiscountCardRepository cardRepository = new DiscountCardSetRepositoryImpl();
        Map<Product, Integer> productCart = new HashMap<>();
        Cashier cashier = new Cashier(1001L, Store.SHOP);
        productCart.put(repository.findById(1L), 4);
        productCart.put(repository.findById(3L), 2);
        productCart.put(repository.findById(5L), 1);
        productCart.put(repository.findById(7L), 3);
        productCart.put(repository.findById(9L), 5);
        ChequeCounter chequeCounter = new ChequeCounter(productCart, cardRepository.findById(1L));
        StringChequeBuilder chequeBuilder = new StringChequeBuilder(chequeCounter);
        chequeBuilder.print(cashier);
    }
}
