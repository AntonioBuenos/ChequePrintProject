package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Position;
import by.smirnov.chequeprintproject.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.smirnov.chequeprintproject.domain.Store.SHOP;
import static org.junit.jupiter.api.Assertions.*;

class StringChequeBuilderTest {

    private static Map<Product, Integer> products = new HashMap<>();
    private StringChequeBuilder chequeBuilder;

    private final String chequeBeginning =
            "----------------------------------------------------------\n" +
                    "                       CASH RECEIPT                       \n" +
                    "                    DrumsticksStore#1                     \n" +
                    "             Minsk, Herearound Str., 111-222              \n" +
                    "                  tel. +375(11)222-33-44                  \n" +
                    "\n" +
                    "CASHIER: 1001                               DATE: 12/20/22\n" +
                    "                                            TIME: ";

    private String chequeEnd =
            "==========================================================\n" +
                    "QTY DESCRIPTION                            PRICE  TOTAL \n" +
                    "\n" +
                    " 4  Vic Firth drumsticks 2B                 14,00$  56,00$\n" +
                    " 5  Vic Firth drumsticks 2BN                14,00$  70,00$\n" +
                    "==========================================================\n" +
                    "SUM                                                   126$\n" +
                    "PROMO DISCOUNT                                          0$\n" +
                    "CARD DISCOUNT                                         6,3$\n" +
                    "TAXABLE TOT.                                        99,75$\n" +
                    "VAT20%                                              19,95$\n" +
                    "\n" +
                    "TOTAL                                               119,7$\n" +
                    "==========================================================\n" +
                    "\n" +
                    "                            ad                            \n" +
                    "\n" +
                    "---------------------------------------------------------";


    @BeforeAll
    static void init() {
        products.put(Product.builder()
                .id(1L)
                .productName("Vic Firth drumsticks 2B")
                .price(14.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build(), 4);
        products.put(Product.builder()
                .id(1L)
                .productName("Vic Firth drumsticks 2BN")
                .price(14.0)
                .isPromoted(false)
                .creationDate(Timestamp.valueOf(LocalDateTime.now()))
                .isDeleted(false).build(), 5);
    }

    @Test
    void buildChequeTest() {
        Cashier cashier = new Cashier(1001L, SHOP);
        String ad = "ad";

        ChequeCounter chequeCounter = new ChequeCounter(
                products,
                new DiscountCard(5L, "John", "John", 5.0, true,
                        Timestamp.valueOf("2022-01-01 00:00:00")));

        chequeBuilder = new StringChequeBuilder(chequeCounter);

        List<Position> productList = new ArrayList<>();
        productList.add(new Position(4, "Vic Firth drumsticks 2B", 14.0, 4 * 14.0));
        productList.add(new Position(5, "Vic Firth drumsticks 2BN", 14.0, 5 * 14.0));

        assertTrue(chequeBuilder.buildCheque(cashier, ad).toString().contains(chequeBeginning));
        assertTrue(chequeBuilder.buildCheque(cashier, ad).toString().contains(chequeEnd));
        /*                assertEquals(chequeBuilder.buildCheque(cashier, ad).toString(), chequeBeginning);*/
    }


}
