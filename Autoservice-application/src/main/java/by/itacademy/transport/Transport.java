package by.itacademy.transport;

import java.util.Objects;

public class Transport {
    /*Поле для хранения типа транспорта. private - т.к. должно быть доступно только внутри класса Transport
    final - т.к. не будет изменяться*/
    private final TransportType transportType;
    /*Поле для хранения модели транспорта. private - т.к. должно быть доступно только внутри класса Transport
    final - т.к. не будет изменяться*/
    private final String model;

    //Конструктор класса Transport. public - т.к. должен быть доступен другим классам в других пакетах
    public Transport(final TransportType transportType, final String model) {
        this.transportType = transportType;
        this.model = model;
    }

    //Метод возвращающий значение поля transportType. public - т.к. должен быть доступен другим классам в других пакетах
    public TransportType getTransportType() {
        return transportType;
    }

    //Метод возвращающий значение поля model. public - т.к. должен быть доступен другим классам в других пакетах
    public String getModel() {
        return model;
    }

    //Метод возвращающий значение поля price Enum TransportType. public - т.к. должен быть доступен другим классам в других пакетах
    public Integer getPrice() {
        return transportType.getPrice();
    }

    //Переопределение метода equals, будет использоваться во время теста парсинга, для сравнения объектов класса Transport
    @Override
    public boolean equals(Object o) {
        //Сравниваем текущий экземпляр объекта this с переданным o, если это один и тот же объект возвращается true
        if (this == o) {
            return true;
        }
        //Проверяем, является ли переданный объект null и какой у него тип, если переданный объект другого типа, то объекты не равны
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        //Сравниваем поля объектов, если два объекта имеют одинаковые значения полей, то объекты совпадают
        Transport transport = (Transport) o;
        return transportType == transport.transportType && Objects.equals(model, transport.model);
    }

    //Переопределяем метод toString для вывода в необходимом нам виде
    @Override
    public String toString() {
        return new String(transportType.name() + " " + model + " " + transportType.getPrice().toString());
    }

}
