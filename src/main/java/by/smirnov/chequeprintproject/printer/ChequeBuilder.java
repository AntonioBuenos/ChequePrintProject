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

import static by.smirnov.chequeprintproject.printer.ChequeConstants.CHEQUE_DIV;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.CHEQUE_DIV_2;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.DECIMAL;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.LINE_WIDTH;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_AD;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_BLANK;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_1;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_2;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_3;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_4;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_POSITION;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FORMAT_POSITIONS_HEADERS;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TABLE_FRAME;
import static by.smirnov.chequeprintproject.printer.ChequeConstants.TITLE;

@RequiredArgsConstructor
public class ChequeBuilder {

    private final ChequeCounter chequeCounter;

    public static void main(String[] args) {
        ProductRepository repository = new ProductSetRepositoryImpl();
        Map<Product, Integer> products = new HashMap<>();
        Store store = new Store("DrumsticksStore#1", "Minsk, here", "+375(33)333-44-55");
        Cashier cashier = new Cashier(1001L, store);
        products.put(repository.findById(1L), 4);
        products.put(repository.findById(3L), 2);
        products.put(repository.findById(5L), 1);
        products.put(repository.findById(7L), 3);
        products.put(repository.findById(9L), 5);
        ChequeCounter chequeCounter = new ChequeCounter(products, DiscountCard
                .builder()
                .id(1001L)
                .holderName("John Smith")
                .holderEmail("js@bondmail.com")
                .discountRate(5.0)
                .isActive(true)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .modificationDate(null)
                .isDeleted(false)
                .build());
        ChequeBuilder chequeBuilder = new ChequeBuilder(chequeCounter);
        chequeBuilder.print(products, cashier);
    }

    public void print(Map<Product, Integer> products, Cashier cashier) {
        StringBuilder cheque = buildCheque(cashier, TABLE_AD);
        System.out.println(cheque);
    }

    public StringBuilder buildCheque(Cashier cashier, String ad) {
        Map<Product, Integer> products = this.chequeCounter.getProducts();
        StringBuilder cheque = new StringBuilder();
        StringBuilder header = buildHeader(cashier);
        StringBuilder positions = buildPositions(products);
        StringBuilder footer = buildFooter(ad);
        cheque
                .append(TABLE_FRAME)
                .append(header)
                .append(CHEQUE_DIV)
                .append(positions)
                .append(CHEQUE_DIV)
                .append(footer)
                .append(TABLE_FRAME);

        return cheque;
    }

    public StringBuilder buildHeader(Cashier cashier) {
        LocalDateTime dateTime = LocalDateTime.now();
        return new StringBuilder()
                .append(centrify(TITLE))
                .append(centrify(cashier.getStore().getName()))
                .append(centrify(cashier.getStore().getAddress()))
                .append(String.format(TABLE_FORMAT_1,
                        StringUtils.center("tel. " + cashier.getStore().getPhoneNumber(), LINE_WIDTH)))
                .append(TABLE_BLANK)
                .append(String.format(TABLE_FORMAT_2, cashier.getId(), dateTime))
                .append(String.format(TABLE_FORMAT_3, dateTime)
                );
    }

    public StringBuilder buildPositions(Map<Product, Integer> products) {
        StringBuilder positions = new StringBuilder();
        positions
                .append(String.format(TABLE_FORMAT_POSITIONS_HEADERS, "QTY", "DESCRIPTION", "PRICE", "TOTAL"))
                .append(TABLE_BLANK);
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            double amount = price * quantity;
            positions
                    .append(String.format(
                                    TABLE_FORMAT_POSITION,
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
                .append(formatLine("SUM", chequeCounter.getGrossChequeAmount()))
                .append(formatLine("PROMO DISCOUNT", chequeCounter.getPromotionDiscountSum()))
                .append(formatLine("CARD DISCOUNT", chequeCounter.getCardDiscountSum()))
                .append(formatLine("TAXABLE TOT.", chequeCounter.getTaxableAmount()))
                .append(formatLine("VAT20%", chequeCounter.getVatAmount()))
                .append(TABLE_BLANK)
                .append(formatLine("TOTAL", chequeCounter.getTotalAmount()))
                .append(CHEQUE_DIV)
                .append(TABLE_BLANK)
                .append(centrify(ad))
                .append(TABLE_BLANK);
    }

    public String centrify(String toBeFormated) {
        return String.format(TABLE_FORMAT_1, StringUtils.center(toBeFormated, LINE_WIDTH));
    }

    public String formatLine(String name, double value) {
        return String.format(TABLE_FORMAT_4, name, new DecimalFormat(DECIMAL).format(value));
    }
}
