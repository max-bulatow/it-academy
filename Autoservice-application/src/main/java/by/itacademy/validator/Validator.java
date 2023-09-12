package by.itacademy.validator;

import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface Validator {
    /*Метод интерфейса Validator, который принмает в качестве аргументов Строки validTransport, invalidTransport и
    коллекцию List, типизируемую классом Transport transportList, а возвращает интерфейс Map c ключом String и значением
    List<Transport>*/
    Map<String, List<Transport>> mapTransportList(final String validTransport, final String invalidTransport, final List<Transport> transportList);
}
