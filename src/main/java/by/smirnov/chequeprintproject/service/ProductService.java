package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    Product findById(Long id);

    ChequeResponse getCheque(ChequeRequest request);

}
