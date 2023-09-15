package by.itacademy.validator.impl;

import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import by.itacademy.validator.FieldValidatorException;
import by.itacademy.validator.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransportValidatorTest {

    /*Тесты выполнения метода transportValidator класса TransportValidator*/
    @Test
    void transportValidator_happyPath() throws FieldValidatorException {
        /*Создаем коллекцию List типизруемую классом Transport; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> transportList = new ArrayList<>();
        /*Создаем коллекцию List для хранения валидного транспорта, типизруемую классом Transport; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> validTransportList = new ArrayList<>();
        /*Создаем коллекцию List для хранения невалидного транспорта типизруемую классом Transport; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> invalidTransportList = new ArrayList<>();

        /*Создается объект класса TransportValidator; final - т.к. его ссылка не должна меняться*/
        final Validator validator = new TransportValidator();

        /*Создаем два объекта класса Transport, валидный и невалдиный; final - т.к. их ссылки не должны меняться*/
        final var validTransport = new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14");
        final var invalidTransport = new Transport(TransportType.AUTOMOBILE, "Audi Q9!№");

        /*Добавляем созданный объекты в коллекцию transportList*/
        transportList.add(validTransport);
        transportList.add(invalidTransport);

        /*Добавляем объект валидного транспорта в коллекцию валидного транспорта*/
        validTransportList.add(validTransport);
        /*Добавляем объект невалидного транспорта в коллекцию невалидного транспорта*/
        invalidTransportList.add(invalidTransport);

        /*Создаем Map в который передаем результат работы метода maptransportList объекта validator*/
        final var mapTransport = validator.mapTransportList("valid", "invalid", transportList);

        /*Проверяем что Map mapTransport не null*/
        assertNotNull(mapTransport, "mapTransport is null");
        /*Проверяем что содержимое фактической коллекции идентично ожидаемой*/
        assertEquals(mapTransport.get("validTransport"), validTransportList, "коллекция невалидного транспорта пуста");
        /*Проверяем что содержимое фактической коллекции идентично ожидаемой*/
        assertEquals(mapTransport.get("invalidTransport"), invalidTransportList, "коллекция невалидного транспорта пуста");

    }
}