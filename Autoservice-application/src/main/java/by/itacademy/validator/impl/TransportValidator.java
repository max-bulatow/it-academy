package by.itacademy.validator.impl;

import by.itacademy.transport.Transport;
import by.itacademy.validator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TransportValidator implements Validator {
    /*Поле modelValidator типа класса Pattern - шаблон для поиска валидных значений моделей транспорта
    private - используется только внутри класса TransportValidator; final - не должна изменяться после инициализации*/
    private final Pattern modelValidator = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$");

    /*Поле типа String, которое будет использоваться в качестве ключа для Map
    public - должно быть доступно другим классам в других пакетах; static - константа должна быть общая для всего класса
    и вызываться без создания объекта класса; final - т.к. не должна изменяться после инициализации*/
    public static final String VALID_TRANSPORT = "validTransport";

    /*Аналогично выше*/
    public static final String INVALID_TRANSPORT = "invalidTransport";

    /*Реализуем метод mapTransportList интерфейса Validator*/
    @Override
    public Map<String, List<Transport>> mapTransportList(final String validTransport,final String invalidTransport,final List<Transport> transportList) {

        /*Объявляется интерфейс Map c ключом типа String и значением List, типизируемом классом Transport для хранения
        коллекций валидного и невалидного транспорта; final - т.к. после его заполнения не должен меняться*/
        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        /*Объявляется коллекция List типизируемая классом Transport для хранения валидного транспорта
        final - т.к. после ее заполнения не должна меняться*/
        final List<Transport> validTransportList = new ArrayList<>();
        /*Аналогично для хранения невалидного транспорта*/
        final List<Transport> invalidTransportList = new ArrayList<>();

        /*В цикле for-each пробегаемся по коллекции transportList с помощью итерационной переменной transport
        final - т.к. во время одной итерации цикла значение transport не должно меняться*/
        for (final Transport transport : transportList) {
            /*Если поле model объекта transport соответствует шаблону matcherValidator, то мы заносим этот объект в
            коллекцию validTransportList, иначе в коллекцию invalidTransportList*/
            if (modelValidator.matcher(transport.getModel()).matches()) {
                validTransportList.add(transport);
            } else {
                invalidTransportList.add(transport);
            }
        }
        /*Помещаем коллекции валидного и невалидного транспорта в созданную ранее HashMap processedTransport*/
        processedTransport.put(VALID_TRANSPORT, validTransportList);
        processedTransport.put(INVALID_TRANSPORT, invalidTransportList);

        /*Метод возвращает заполненный HashMap processedTransport*/
        return processedTransport;
    }

}
