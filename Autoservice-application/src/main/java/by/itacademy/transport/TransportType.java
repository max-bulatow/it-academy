package by.itacademy.transport;

public enum TransportType {
    MOTORCYCLE(10),
    AUTOMOBILE(20),
    MINIBUS(30);

    /*приватное поле price, для хранения цены диагностики типов транспорта, указанных в нашем Enum.
    final - потому-что это значение фиксированно и не должно меняться*/
    private final Integer price;

    //Конструктор нашего Enum
    TransportType(final Integer price) {
        this.price = price;
    }

    //Метод, который возвращает значение поля price. public - т.к. этот метод должен быть доступен в других классах
    public Integer getPrice() {
        return price;
    }
}
