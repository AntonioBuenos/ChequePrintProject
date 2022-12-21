package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Promotion;
import lombok.Getter;
import org.apache.commons.math3.util.Precision;

import java.math.BigDecimal;
import java.util.Map;

@Getter
public class ChequeCounter {

    private final Map<Product, Integer> products;
    private final DiscountCard card;
    private double totalAmount;
    private double vatAmount;
    private double promotionDiscountSum;
    private double taxableAmount;
    private double grossChequeAmount;
    private double cardDiscountSum;

    public ChequeCounter(Map<Product, Integer> products, DiscountCard card) {
        this.products = products;
        this.card = card;
        processCheque(products);
    }

    private void processCheque(Map<Product, Integer> products) {
        int promoGoodsQty = 0;
        BigDecimal total = new BigDecimal(0);
        BigDecimal promoAmount = new BigDecimal(0);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            BigDecimal amount = BigDecimal.valueOf(product.getPrice() * entry.getValue());
            if (Boolean.TRUE.equals(product.getIsPromoted())) {
                promoGoodsQty += entry.getValue();
                promoAmount = promoAmount.add(amount);
            }
            total = total.add(amount);
        }
        grossChequeAmount = total.doubleValue();
        promotionDiscountSum = countPromotionDiscount(promoGoodsQty, promoAmount);
        cardDiscountSum = countCardDiscount();
        totalAmount = countTotalAmount();
        vatAmount = countVatAmount();
        taxableAmount = countTaxableAmount();
    }

    private double countCardDiscount() {
        return Precision.round(card.getDiscountRate() * grossChequeAmount / 100, 2);
    }

    private double countPromotionDiscount(int promoGoodsQty, BigDecimal promoAmount) {
        if (promoGoodsQty >= Promotion.minimalGoodsQty) {
            return promoAmount.multiply(BigDecimal.valueOf(Promotion.promotionalDiscount / 100)).doubleValue();
        }
        return 0;
    }

    private double countTotalAmount() {
        return grossChequeAmount - (promotionDiscountSum + cardDiscountSum);
    }

    private double countVatAmount() {
        return Precision.round(totalAmount / 120 * 20, 2);
    }

    private double countTaxableAmount() {
        return totalAmount - vatAmount;
    }

}
