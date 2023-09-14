package by.itacademy.reader.impl;

import by.itacademy.parser.TransportParserException;
import by.itacademy.parser.impl.TransportJsonParser;
import by.itacademy.reader.TransportReaderException;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

class TransportJsonReaderTest {

    /*Объявляем переменную типа Sting, в которой будем хранить имя файла тестируемого транспорта
    final - т.к. ее ссылка не должна меняться*/
    private final String testFileName = "testTransport.json";

    /*Объявляем переменную типа String, в которой будем хранить контент для тестирования метода чтения
    final - т.к. ее ссылка не должна меняться*/
    private final String testFileContent = "[  {    \"type\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }]";

    /*Тест выполнения метода read класса TransportJsonReader*/
    @Test
    void testRead_happyPath() throws TransportParserException, TransportReaderException {
        /*Создаем коллекцию List типизруемую классом Transport; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> transportList = new ArrayList<>();
        /*Добавляем в коллекцию новые объекты класса Transport*/
        transportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"));
        transportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));

        /*Создаем переменную parser которой присваиваем mock объект класса TransportJsonParser; final - т.к. ее ссылка не должна меняться */
        final var parser = Mockito.mock(TransportJsonParser.class);
        /*Задаем правило поведения mock-объекта, что при вызове метода parse будет возвращаться transportList*/
        Mockito.when(parser.parse(testFileContent)).thenReturn(transportList);

        /*Создаем объект класса TransportJsonReader и передаем в его конструктор имя файла и parser
        final - т.к. его ссылка не должна меняться*/
        final var reader = new TransportJsonReader(testFileName, parser);
        /*Создаем коллекцию List типизируемую классом Transport в которой будем хранить результат работы метода read
        final - т.к. ее ссылка не должна меняться*/
        final var actualTransport = reader.read();

        /*Проверяем что фактическая коллекция идентична ожидаемой*/
        Assertions.assertEquals(actualTransport, transportList);
        /*Проверяем что был однократно вызван метод parse*/
        Mockito.verify(parser).parse(testFileContent);
        /*Проверяем что не было больше неверефицироанных обращений к mock-объекту - к любым его методам*/
        Mockito.verifyNoMoreInteractions(parser);

    }

    /*Тест метода на вызывание исключений*/
    @Test
    void testRead_parserThrowException() throws TransportParserException {
        /*Создаем переменную parser которой присваиваем mock объект класса TransportJsonParser
        final - т.к. его ссылка не должна меняться */
        final var parser = Mockito.mock(TransportJsonParser.class);
        /*Задаем поведение mock-объекта чтобы он вызвал исключение TranportParserException при работе метода parse*/
        Mockito.doThrow(TransportParserException.class).when(parser).parse(testFileContent);

        /*Создаем объект класса TransportJsonReader и передаем в его конструктор имя файла и parser
        final - т.к. его ссылка не должна меняться*/
        final var reader = new TransportJsonReader(testFileName, parser);
        /*Создаем переменную в которую передаем результат работы метода поверки вызова исключений
        final - т.к. ее ссылка не должна меняться*/
        final var error = Assertions.assertThrowsExactly(TransportReaderException.class, reader::read);

        /*Проверяем что фактическое сообщение об ошибке идентично ожидаемому*/
        Assertions.assertEquals(error.getMessage(), "Ошибка обработки содержимого файла [testTransport.json]");
        /*Проверяем что был однократно вызван метод parse*/
        Mockito.verify(parser).parse(testFileContent);
        /*Проверяем что не было больше неверефицироанных обращений к mock-объекту - к любым его методам*/
        Mockito.verifyNoMoreInteractions(parser);
    }
}