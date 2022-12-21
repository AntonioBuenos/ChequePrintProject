package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.Position;
import by.smirnov.chequeprintproject.domain.Product;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.smirnov.chequeprintproject.service.chequebuilder.ChequeConstants.AD;

@RequiredArgsConstructor
public class EntityChequeBuilder implements ChequeBuilder<ChequeResponse> {

    private final ChequeCounter chequeCounter;

    public ChequeResponse buildCheque(Cashier cashier) {
        LocalDateTime dateTime = LocalDateTime.now();
        Map<Product, Integer> products = chequeCounter.getProducts();
        return ChequeResponse.builder()
                .title(ChequeConstants.TITLE)
                .storeName(cashier.getStore().getName())
                .address(cashier.getStore().getAddress())
                .phoneNumber(ChequeConstants.PHONE + cashier.getStore().getPhoneNumber())
                .cashierNumber(cashier.getId())
                .date(dateTime.toLocalDate())
                .time(dateTime.toLocalTime())
                .positions(buildPositions(products))
                .sum(chequeCounter.getGrossChequeAmount())
                .promoDiscount(chequeCounter.getPromotionDiscountSum())
                .cardDiscount(chequeCounter.getCardDiscountSum())
                .taxable(chequeCounter.getTaxableAmount())
                .vat(chequeCounter.getVatAmount())
                .total(chequeCounter.getTotalAmount())
                .ad(AD)
                .build();
    }

    List<Position> buildPositions(Map<Product, Integer> products) {
        List<Position> positions = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            positions.add(new Position(quantity, product.getProductName(), price, price * quantity));
        }
        return positions;
    }
}
