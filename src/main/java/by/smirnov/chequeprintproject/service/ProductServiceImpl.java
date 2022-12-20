package by.smirnov.chequeprintproject.service;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.exception.NoSuchEntityException;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.service.chequebuilder.EntityChequeBuilder;
import by.smirnov.chequeprintproject.repository.ProductDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDBRepository repository;
    private final DiscountCardService discountCardService;

    @Override
    public Product findById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(NoSuchEntityException::new);
    }

    @Override
    public ChequeResponse getCheque(ChequeRequest request) {
        DiscountCard card = discountCardService.findById(request.getCardId());
        ChequeCounter chequeCounter = new ChequeCounter(convertCart(request.getProducts()), card);
        return new EntityChequeBuilder(chequeCounter).print(Cashier.getById(request.getCashierId()));
    }

    private Map<Product, Integer> convertCart(Map<Long, Integer> cart){
        Map<Product, Integer> products = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Product product = findById(entry.getKey());
            products.put(product, entry.getValue());
        }
        return products;
    }

}
