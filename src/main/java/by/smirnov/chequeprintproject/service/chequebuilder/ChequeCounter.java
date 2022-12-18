package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Promotion;
import lombok.Getter;

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
        double total = 0;
        double promoAmount = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            double amount = product.getPrice() * entry.getValue();
            if (Boolean.TRUE.equals(product.getIsPromoted())) {
                promoGoodsQty += entry.getValue();
                promoAmount += amount;
            }
            total += amount;
        }
        grossChequeAmount = total;
        promotionDiscountSum = countPromotionDiscount(promoGoodsQty, promoAmount);
        cardDiscountSum = countCardDiscount();
        totalAmount = countTotalAmount();
        vatAmount = countVatAmount();
        taxableAmount = countTaxableAmount();
    }

    private double countCardDiscount() {
        return card.getDiscountRate() * grossChequeAmount / 100;
    }

    private double countPromotionDiscount(int promoGoodsQty, double promoAmount) {
        if (promoGoodsQty >= Promotion.minimalGoodsQty) {
            return promoAmount * Promotion.promotionalDiscount / 100;
        }
        return 0;
    }

    private double countTotalAmount() {
        return grossChequeAmount - (promotionDiscountSum + cardDiscountSum);
    }

    private double countVatAmount() {
        return totalAmount / 120 * 20;
    }

    private double countTaxableAmount() {
        return totalAmount - vatAmount;
    }

}
