package by.smirnov.chequeprintproject.printer;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import by.smirnov.chequeprintproject.repository.ProductRepository;
import by.smirnov.chequeprintproject.repository.ProductSetRepositoryImpl;
import org.apache.commons.lang3.StringUtils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ChequeBuilder {

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
        ChequeBuilder chequeBuilder = new ChequeBuilder();
        chequeBuilder.print(products, cashier);
    }

    public void print(Map<Product, Integer> products, Cashier cashier) {
        StringBuilder cheque = buildCheque(products, cashier, ChequeConstants.TABLE_AD);
        System.out.println(cheque);

    }

    public StringBuilder buildCheque(Map<Product, Integer> products, Cashier cashier, String ad) {
        StringBuilder cheque = new StringBuilder();
        StringBuilder positions = buildPositions(products);
        LocalDateTime dateTime = LocalDateTime.now();
        cheque
                .append(ChequeConstants.TABLE_FRAME)
                .append(String.format(ChequeConstants.TABLE_FORMAT_1, StringUtils.center(ChequeConstants.TITLE, ChequeConstants.LINE_WIDTH)))
                .append(String.format(ChequeConstants.TABLE_FORMAT_1, StringUtils.center(cashier.getStore().getName(), ChequeConstants.LINE_WIDTH)))
                .append(String.format(ChequeConstants.TABLE_FORMAT_1, StringUtils.center(cashier.getStore().getAddress(), ChequeConstants.LINE_WIDTH)))
                .append(String.format(ChequeConstants.TABLE_FORMAT_1,
                        StringUtils.center("tel. " + cashier.getStore().getPhoneNumber(), ChequeConstants.LINE_WIDTH)))
                .append(ChequeConstants.TABLE_BLANK)
                .append(String.format(ChequeConstants.TABLE_FORMAT_2, cashier.getId(), dateTime))
                .append(String.format(ChequeConstants.TABLE_FORMAT_3, dateTime))
                .append(ChequeConstants.CHEQUE_DIV_2)
                .append(String.format(ChequeConstants.TABLE_FORMAT_POSITIONS_HEADERS, "QTY", "DESCRIPTION", "PRICE", "TOTAL"))
                .append(ChequeConstants.TABLE_BLANK)
                .append(positions)
                .append(ChequeConstants.CHEQUE_DIV)
                .append(String.format(ChequeConstants.TABLE_FORMAT_4, "DISCOUNT TOT.", new DecimalFormat(ChequeConstants.DECIMAL).format(10.01)))
                .append(String.format(ChequeConstants.TABLE_FORMAT_4, "TAXABLE TOT.", 541.27))
                .append(String.format(ChequeConstants.TABLE_FORMAT_4, "VAT20%", 20.01))
                .append(String.format(ChequeConstants.TABLE_FORMAT_4, "TOTAL", 563.91))
                .append(ChequeConstants.CHEQUE_DIV)
                .append(ChequeConstants.TABLE_BLANK)
                .append(String.format(ChequeConstants.TABLE_FORMAT_1, StringUtils.center(ad, ChequeConstants.LINE_WIDTH)))
                .append(ChequeConstants.TABLE_BLANK)
                .append(ChequeConstants.TABLE_FRAME);

        return cheque;
    }

    public StringBuilder buildPositions(Map<Product, Integer> products) {
        StringBuilder positions = new StringBuilder();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            double price = product.getPrice();
            double amount = price * quantity;
            positions
                    .append(String.format(
                                    ChequeConstants.TABLE_FORMAT_POSITION,
                                    quantity,
                                    product.getProductName(),
                                    price,
                                    amount
                            )
                    );
        }
        return positions;
    }
}
