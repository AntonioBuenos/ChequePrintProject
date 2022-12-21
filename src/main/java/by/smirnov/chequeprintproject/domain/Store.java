package by.smirnov.chequeprintproject.domain;

import lombok.Getter;

@Getter
public enum Store {

    SHOP(
            "DrumsticksStore#1",
            "Minsk, Herearound Str., 111-222",
            "+375(11)222-33-44"
    );

    private final String name;
    private final String address;
    private final String phoneNumber;

    Store(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
