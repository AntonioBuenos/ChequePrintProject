package by.smirnov.chequeprintproject.service.chequebuilder;

import by.smirnov.chequeprintproject.domain.Cashier;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.domain.DiscountCard;
import by.smirnov.chequeprintproject.domain.Position;
import by.smirnov.chequeprintproject.domain.Product;
import by.smirnov.chequeprintproject.domain.Store;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.smirnov.chequeprintproject.domain.Store.SHOP;
import static by.smirnov.chequeprintproject.service.chequebuilder.ChequeConstants.AD;
import static by.smirnov.chequeprintproject.service.chequebuilder.ChequeConstants.TITLE;
import static org.junit.jupiter.api.Assertions.*;

class EntityChequeBuilderTest {

    private static Map<Product, Integer> products = new HashMap<>();
    private EntityChequeBuilder chequeBuilder;

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
    void buildPositionsTest() {
        chequeBuilder = new EntityChequeBuilder(
                new ChequeCounter(
                        products,
                        new DiscountCard(5L, "John", "John", 5.0, true, Timestamp.valueOf("2022-01-01 00:00:00"))));

        List<Position> expected = new ArrayList<>();
        expected.add(new Position(4, "Vic Firth drumsticks 2B", 14.0, 4 * 14.0));
        expected.add(new Position(5, "Vic Firth drumsticks 2BN", 14.0, 5 * 14.0));

        assertEquals(expected, chequeBuilder.buildPositions(products));
    }

    @Test
    void buildChequeTest() {
        Cashier cashier = new Cashier(1001L, SHOP);

        ChequeCounter chequeCounter = new ChequeCounter(
                products,
                new DiscountCard(5L, "John", "John", 5.0, true,
                        Timestamp.valueOf("2022-01-01 00:00:00")));

        chequeBuilder = new EntityChequeBuilder(chequeCounter);

        List<Position> productList = new ArrayList<>();
        productList.add(new Position(4, "Vic Firth drumsticks 2B", 14.0, 4 * 14.0));
        productList.add(new Position(5, "Vic Firth drumsticks 2BN", 14.0, 5 * 14.0));

        LocalDateTime dateTime = LocalDateTime.now();
        ChequeResponse expected = ChequeResponse.builder()
                .title("CASH RECEIPT")
                .storeName("DrumsticksStore#1")
                .address("Minsk, Herearound Str., 111-222")
                .phoneNumber("tel. +375(11)222-33-44")
                .cashierNumber(1001L)
                .date(dateTime.toLocalDate())
                .time(dateTime.toLocalTime())
                .positions(productList)
                .sum(126.0)
                .promoDiscount(0.0)
                .cardDiscount(6.3)
                .taxable(99.75)
                .vat(19.95)
                .total(119.7)
                .ad(AD)
                .build();

        assertEquals(expected, chequeBuilder.buildCheque(cashier, AD));
        assertEquals(expected, chequeBuilder.print(cashier));
    }
}
