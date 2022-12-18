package by.smirnov.chequeprintproject.domain;

public enum Store {

    SHOP(
            "DrumsticksStore#1",
            "Minsk, Herearound Str., 111-222",
            "+375(11)222-33-44"
    );

    public String name;
    public String address;
    public String phoneNumber;

    private static Store instance;

    Store(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
