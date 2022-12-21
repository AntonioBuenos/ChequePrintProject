package by.smirnov.chequeprintproject.service.restservice;

import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.Product;

public interface ProductService {

    Product findById(Long id);

    ChequeResponse getCheque(ChequeRequest request);

}
