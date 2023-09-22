package by.itacademy.model;

public enum TransportType {
    MOTORCYCLE(10),
    AUTOMOBILE(20),
    MINIBUS(30);

    private final Integer price;

    TransportType(final Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }
}

