package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;

public interface ChequeBuilder<T> {

    T buildCheque(Cashier cashier);

}
