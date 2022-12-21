package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;

import static by.smirnov.chequeprintproject.service.chequebuilder.ChequeConstants.AD;

@RequiredArgsConstructor
public class StringChequeBuilder implements ChequeBuilder<StringBuilder> {

    private final ChequeCounter chequeCounter;

    public StringBuilder buildCheque(Cashier cashier) {
        Map<Product, Integer> products = this.chequeCounter.getProducts();
        StringBuilder cheque = new StringBuilder();
        StringBuilder header = buildHeader(cashier);
        StringBuilder positions = buildPositions(products);
        StringBuilder footer = buildFooter(AD);
        cheque
                .append(ChequeConstants.CHEQUE_FRAME)
                .append(header)
                .append(ChequeConstants.CHEQUE_DIV)
                .append(positions)
                .append(ChequeConstants.CHEQUE_DIV)
                .append(footer)
                .append(ChequeConstants.CHEQUE_FRAME);

        return cheque;
    }

    public StringBuilder buildHeader(Cashier cashier) {
        LocalDateTime dateTime = LocalDateTime.now();
        return new StringBuilder()
                .append(centrify(ChequeConstants.TITLE))
                .append(centrify(cashier.getStore().getName()))
                .append(centrify(cashier.getStore().getAddress()))
                .append(centrify(ChequeConstants.PHONE + cashier.getStore().getPhoneNumber()))
                .append(ChequeConstants.BLANK_LINE)
                .append(String.format(ChequeConstants.FORMAT_DATE, cashier.getId(), dateTime))
                .append(String.format(ChequeConstants.FORMAT_TIME, ChequeConstants.TIME, dateTime)
                );
    }

    public StringBuilder buildPositions(Map<Product, Integer> products) {
        StringBuilder positions = new StringBuilder();
        positions
                .append(String.format(ChequeConstants.FORMAT_POSITIONS_HEADERS,
                        ChequeConstants.QTY,
                        ChequeConstants.DESC,
                        ChequeConstants.PRICE,
                        ChequeConstants.TOTAL))
                .append(ChequeConstants.BLANK_LINE);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            positions.append(formatPosition(entry));
        }
        return positions;
    }

    private String formatPosition(Map.Entry<Product, Integer> entry) {
        Product product = entry.getKey();
        int quantity = entry.getValue();
        double price = product.getPrice();
        double amount = price * quantity;
        return String.format(ChequeConstants.FORMAT_POSITION,
                quantity,
                normalizeLength(product.getProductName(), 38),
                price,
                amount
        );
    }

    public StringBuilder buildFooter(String ad) {
        return new StringBuilder()
                .append(formatLine(ChequeConstants.SUM, chequeCounter.getGrossChequeAmount()))
                .append(formatLine(ChequeConstants.PROMO_DISC, chequeCounter.getPromotionDiscountSum()))
                .append(formatLine(ChequeConstants.CARD_DISC, chequeCounter.getCardDiscountSum()))
                .append(formatLine(ChequeConstants.TAXABLE, chequeCounter.getTaxableAmount()))
                .append(formatLine(ChequeConstants.VAT, chequeCounter.getVatAmount()))
                .append(ChequeConstants.BLANK_LINE)
                .append(formatLine(ChequeConstants.TOTAL_AMT, chequeCounter.getTotalAmount()))
                .append(ChequeConstants.CHEQUE_DIV)
                .append(ChequeConstants.BLANK_LINE)
                .append(centrify(ad))
                .append(ChequeConstants.BLANK_LINE);
    }

    public String centrify(String toBeFormated) {
        return String.format(ChequeConstants.FORMAT_LINE, StringUtils.center(toBeFormated, ChequeConstants.LINE_WIDTH));
    }

    public String formatLine(String name, double value) {
        return String.format(ChequeConstants.FORMAT_FOOTER, name, new DecimalFormat(ChequeConstants.DECIMAL).format(value));
    }

    public String normalizeLength(String line, int length) {
        if (line.length() >= length) return line.substring(0, length);
        return line;
    }
}
