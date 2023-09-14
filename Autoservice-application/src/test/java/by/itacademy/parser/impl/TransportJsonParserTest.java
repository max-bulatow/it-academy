package by.itacademy.parser.impl;

import by.itacademy.parser.TransportParserException;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportJsonParserTest {

    /*Тест выполнения метода parse класса TransportJsonParser*/
    @Test
    void testParse_happyPath() throws TransportParserException {
        /*Создаем коллекцию List типизруемую классом Transport; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> transportList = new ArrayList<>();
        /*Добавляем в коллекцию новые объекты класса Transport*/
        transportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"));
        transportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));

        /*Создаем переменную типа String c содержимым которое потом будем "парсить" методом parse
        final - т.к. ее ссылка не должна меняться*/
        final String content = "[  {    \"type\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }  ]";

        /*Создаем объект класса TransportJsonParser; final - т.к. его ссылка не должна меняться*/
        final TransportJsonParser parser = new TransportJsonParser();

        /*Создаем коллекцию List типизируемую классом Transport в которую передаем результат работы метода parse объекта
        parser, в который в качестве аргумента передаем строку content; final - т.к ее ссылка не должна меняться*/
        final List<Transport> transportListParsing = parser.parse(content);

        /*Проверяем что коллекция transportListParsing не null*/
        assertNotNull(transportListParsing, "transport list is null");
        /*Проверяем что содержимое "фактической" коллекции такое же как содержимое "ожидаемой" колекции*/
        assertEquals(transportListParsing, transportList);
    }

    /*Тест метода на вызывание исключений*/
    @Test
    void testParse_parserThrowException() {
        /*Создаем переменную типа String c содержимым которое потом будем "парсить" методом parse
         final - т.к. ее ссылка не должна меняться*/
        final String content = "[  {    \"ttype\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }  ]";
        /*Создаем объект класса TransportJsonParser; final - т.к. его ссылка не должна меняться*/
        final TransportJsonParser parser = new TransportJsonParser();

        /*Создаем объект класса Exception в который передаем результат работы метода, который ожидает что метод parse
        вызовет исключение TransportParserException*/
        final Exception transportParserException = assertThrows(TransportParserException.class, () -> parser.parse(content));

        /*Проверяем что объект transportParserException не null */
        assertNotNull(transportParserException, "transportParserException is null");
        /*Проверяем что сообщение фактического исключения идентично ожидаемому*/
        assertEquals("Ошибка парсинга содержимого JSON файла", transportParserException.getMessage());
    }

}