package by.itacademy.sorter;

import by.itacademy.transport.Transport;

import java.util.Comparator;

//Enum список типов сортировки
public enum SortingType {
    /*Статический метод comparing в который передаем лямбда-выражение для сравнения объектов transport по типу*/
    TYPE(Comparator.comparing(transport -> transport.getTransportType().name())),
    /*Сравниваем транспорт по модели*/
    MODEL(Comparator.comparing(Transport::getModel)),
    /*Сравниваем транспорт по цене*/
    PRICE(Comparator.comparing(Transport::getPrice));

    /*private - поле доступно только внутри enum SortingType; final - т.к. его ссылка не должна меняться
    В поле храним интерфейс comparator типизируемый классом Transport, будем использвать для сортировки транспорта*/
    private final Comparator<Transport> comparator;

    //Конструктор enum SortingType
    SortingType(final Comparator<Transport> comparator) {
        this.comparator = comparator;
    }

    //Метод который возвращает comparator; public - т.к. должен быть доступен другим классам в других пакетах
    public Comparator<Transport> getComparator() {
        return comparator;
    }
}
