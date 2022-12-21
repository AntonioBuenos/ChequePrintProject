package by.smirnov.chequeprintproject.service.restservice;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.exceptionhandler.NoSuchEntityException;
import by.smirnov.chequeprintproject.repository.ProductDBRepository;
import by.smirnov.chequeprintproject.service.chequebuilder.ChequeCounter;
import by.smirnov.chequeprintproject.service.chequebuilder.EntityChequeBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static by.smirnov.chequeprintproject.exceptionhandler.ExceptionConstants.CARD_NOT_FOUND_MESSAGE;
import static by.smirnov.chequeprintproject.exceptionhandler.ExceptionConstants.CASHIER_NOT_FOUND_MESSAGE;
import static by.smirnov.chequeprintproject.exceptionhandler.ExceptionConstants.PRODUCT_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDBRepository repository;
    private final DiscountCardService discountCardService;

    @Override
    public Product findById(Long id) {
        return repository
                .findById(id)
                .orElse(null);
    }

    @Override
    public ChequeResponse getCheque(ChequeRequest request) {
        Long cardId = request.getCardId();
        DiscountCard card = discountCardService.findById(cardId);
        if (card == null) throw new NoSuchEntityException(CARD_NOT_FOUND_MESSAGE + cardId);
        ChequeCounter chequeCounter = new ChequeCounter(
                convertCart(request.getProducts()),
                card);
        EntityChequeBuilder chequeBuilder = new EntityChequeBuilder(chequeCounter);
        Cashier cashier = Cashier.getById(request.getCashierId());
        if (cashier == null) throw new NoSuchEntityException(CASHIER_NOT_FOUND_MESSAGE + cardId);
        return chequeBuilder.buildCheque(cashier);
    }

    private Map<Product, Integer> convertCart(Map<Long, Integer> cart) {
        Map<Product, Integer> products = new HashMap<>();
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Long productId = entry.getKey();
            Product product = findById(productId);
            if (product==null) throw new NoSuchEntityException(PRODUCT_NOT_FOUND_MESSAGE + productId);
            products.put(product, entry.getValue());
        }
        return products;
    }

}
