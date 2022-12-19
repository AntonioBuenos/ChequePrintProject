package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.Product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;

import static by.smirnov.chequeprintproject.util.PathGetter.getPath;

@RequiredArgsConstructor
public class StringChequeBuilder {

    private final ChequeCounter chequeCounter;

    public void print(Cashier cashier) {
        StringBuilder cheque = buildCheque(cashier, ChequeConstants.AD);
        writeToFile(cheque);
        System.out.println(cheque);
    }

    public void writeToFile(StringBuilder cheque) {
        String path = getPath("Cheques.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println(cheque);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public StringBuilder buildCheque(Cashier cashier, String ad) {
        Map<Product, Integer> products = this.chequeCounter.getProducts();
        StringBuilder cheque = new StringBuilder();
        StringBuilder header = buildHeader(cashier);
        StringBuilder positions = buildPositions(products);
        StringBuilder footer = buildFooter(ad);
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
                .append(centrify(cashier.getStore().name))
                .append(centrify(cashier.getStore().address))
                .append(centrify(ChequeConstants.PHONE + cashier.getStore().phoneNumber))
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
