package by.smirnov.chequeprintproject.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cashier {

    private final Long id;
    private final Store store;

    private static List<Cashier> cashiers;

    static {
        cashiers = new ArrayList<>();
        cashiers.add(new Cashier(1001L, Store.SHOP));
        cashiers.add(new Cashier(1002L, Store.SHOP));
        cashiers.add(new Cashier(1003L, Store.SHOP));
    }

    public static Cashier getById(Long id){
        for (Cashier cashier : cashiers) {
            if(Objects.equals(cashier.id, id)) return cashier;
        }
        return null;
    }
}
