package by.itacademy.transport;

//Enum список типов нашего автотранспорта
public enum TransportType {
    MOTORCYCLE(10),
    AUTOMOBILE(20),
    MINIBUS(30);

    /*Приватное поле price, для хранения цены диагностики типов транспорта, указанных в нашем Enum.
    final - т.к его ссылка не должна меняться*/
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
