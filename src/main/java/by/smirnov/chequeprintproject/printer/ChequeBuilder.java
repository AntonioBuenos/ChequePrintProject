package by.smirnov.chequeprintproject.printer;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.repository.ProductRepository;
import by.smirnov.chequeprintproject.repository.ProductSetRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static by.smirnov.chequeprintproject.printer.ChequeConstants.CARD_DISC;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.CHEQUE_DIV;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.DECIMAL;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.DESC;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.LINE_WIDTH;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.AD;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.BLANK_LINE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_LINE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_DATE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_TIME;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_FOOTER;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_POSITION;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.FORMAT_POSITIONS_HEADERS;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.CHEQUE_FRAME;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.PHONE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.PRICE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.PROMO_DISC;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.QTY;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.SUM;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TAXABLE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TIME;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TITLE;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TOTAL_AMT;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TOTAL;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.VAT;

@RequiredArgsConstructor
public class ChequeBuilder {

    private final ChequeCounter chequeCounter;

    public void print(Cashier cashier) {
        StringBuilder cheque = buildCheque(cashier, AD);
        System.out.println(cheque);
    }

    public StringBuilder buildCheque(Cashier cashier, String ad) {
        Map<Product, Integer> products = this.chequeCounter.getProducts();
        StringBuilder cheque = new StringBuilder();
        StringBuilder header = buildHeader(cashier);
        StringBuilder positions = buildPositions(products);
        StringBuilder footer = buildFooter(ad);
        cheque
                .append(CHEQUE_FRAME)
                .append(header)
                .append(CHEQUE_DIV)
                .append(positions)
                .append(CHEQUE_DIV)
                .append(footer)
                .append(CHEQUE_FRAME);

        return cheque;
    }

    public StringBuilder buildHeader(Cashier cashier) {
        LocalDateTime dateTime = LocalDateTime.now();
        return new StringBuilder()
                .append(centrify(TITLE))
                .append(centrify(cashier.getStore().name))
                .append(centrify(cashier.getStore().address))
                .append(centrify(PHONE + cashier.getStore().phoneNumber))
                .append(BLANK_LINE)
                .append(String.format(FORMAT_DATE, cashier.getId(), dateTime))
                .append(String.format(FORMAT_TIME, TIME, dateTime)
                );
    }

    public StringBuilder buildPositions(Map<Product, Integer> products) {
        StringBuilder positions = new StringBuilder();
        positions
                .append(String.format(FORMAT_POSITIONS_HEADERS, QTY, DESC, PRICE, TOTAL))
                .append(BLANK_LINE);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            double amount = price * quantity;
            positions
                    .append(String.format(FORMAT_POSITION,
                                    quantity,
                                    product.getProductName(),
                                    price,
                                    amount
                            )
                    );
        }
        return positions;
    }

    public StringBuilder buildFooter(String ad) {
        return new StringBuilder()
                .append(formatLine(SUM, chequeCounter.getGrossChequeAmount()))
                .append(formatLine(PROMO_DISC, chequeCounter.getPromotionDiscountSum()))
                .append(formatLine(CARD_DISC, chequeCounter.getCardDiscountSum()))
                .append(formatLine(TAXABLE, chequeCounter.getTaxableAmount()))
                .append(formatLine(VAT, chequeCounter.getVatAmount()))
                .append(BLANK_LINE)
                .append(formatLine(TOTAL_AMT, chequeCounter.getTotalAmount()))
                .append(CHEQUE_DIV)
                .append(BLANK_LINE)
                .append(centrify(ad))
                .append(BLANK_LINE);
    }

    public String centrify(String toBeFormated) {
        return String.format(FORMAT_LINE, StringUtils.center(toBeFormated, LINE_WIDTH));
    }

    public String formatLine(String name, double value) {
        return String.format(FORMAT_FOOTER, name, new DecimalFormat(DECIMAL).format(value));
    }
}
