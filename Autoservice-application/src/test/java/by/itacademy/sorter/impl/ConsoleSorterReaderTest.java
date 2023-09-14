package by.itacademy.sorter.impl;

import by.itacademy.sorter.ConsoleReader;
import by.itacademy.sorter.SorterReaderException;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsoleSorterReaderTest {

    /*Создаем HashMap для хранения коллекции валидного транспорта; private - т.к. будет использоваться только внутри класса
    final - т.к. его ссылка не должна меняться*/
    private final Map<String, List<Transport>> processedTransport = new HashMap<>();
    /*Создаем поле типа String в котором будем хранить ключ для HashMap; private - т.к. будет использоваться только внутри класса
    final - т.к. его ссылка не должна меняться*/
    private final String validTransport = "validTransport";

    /*Действия которые будут выполняться перед каждым теcтом*/
    @BeforeEach
    void setUp() {
        /*Создаем коллекцию валидного транспорта; final - т.к. ее ссылка не должна меняться*/
        final List<Transport> validTransport = new ArrayList<>();
        /*Добавляем объекты класса Transport в коллекцию валидного транспорта*/
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "BMW M5"));
        validTransport.add(new Transport(TransportType.MINIBUS, "Sprinter264"));

        /*Добавляем коллекцию валидного транспорта в HashMap с ключом validTransport*/
        processedTransport.put(this.validTransport, validTransport);
    }

    /*Тест выполнения метода readSorting класса ConsoleSoterReader*/
    @Test
    void readSorting_happyPath() throws SorterReaderException {
        /*Объявляем переменную типа String в которой будем хранить ожидаемый результат работы метода; final - т.к. ее
        ссылка не должна меняться*/
        final String expected = "[MINIBUS Sprinter264 30, AUTOMOBILE Audi Q7 20, AUTOMOBILE BMW M5 20]";
        /*Создаем переменную consoleReader которой присваиваем mock-объект класса ConsoleReader; final - т.к. его ссылка
        не должна меняться*/
        final ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        /*Создаем объект класса ConsoleSorterReader и передаем в его конструктор consoleReader; final - т.к. его ссылка
        не должна меняться*/
        final ConsoleSorterReader reader = new ConsoleSorterReader(consoleReader);
        /*Задаем правило поведения mock-объекта, что при вызове метода nextLine будут возвращаться тип и направление сортировки*/
        Mockito.when(consoleReader.nextLine()).thenReturn("model forward").thenReturn("type forward").thenReturn("price reverse");

        /*Создаем Map в котором будем хранить результат работы метода readSorting объекта reader; final - т.к. его ссылка не должна меняться*/
        final Map<String, List<Transport>> actualSorter = reader.readSorting(processedTransport);
        /*Создаем переменную типа String в которой будем хранить коллекцию валидного транспорта в строковом виде; final -
        т.к. ее ссылка не должна меняться*/
        final String actual = actualSorter.get(validTransport).toString();

        /*Проверяем что фактический результат идентичен ожадаемому*/
        Assertions.assertEquals(expected, actual);
        /*Проверяем что метод nextLine был вызван 3 раза*/
        Mockito.verify(consoleReader, Mockito.times(3)).nextLine();
        /*Проверяем что не было больше неверефицироанных обращений к mock-объекту - к любым его методам*/
        Mockito.verifyNoMoreInteractions(consoleReader);
    }

    /*Тест на вызывание исключений метода readSorting*/
    @Test
    void readSorting_throwException() {
        /*Создаем переменную consoleReader которой присваиваем mock-объект класса ConsoleReader; final - т.к. его ссылка
        не должна меняться*/
        final ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        /*Создаем объект класса ConsoleSorterReader и передаем в его конструктор consoleReader; final - т.к. его ссылка
        не должна меняться*/
        final ConsoleSorterReader reader = new ConsoleSorterReader(consoleReader);
        /*Задаем правило поведения mock-объекта, что при вызове метода nextLine будут возвращаться тип и направление сортировки*/
        Mockito.when(consoleReader.nextLine()).thenReturn("modell forward").thenReturn("type forward").thenReturn("price reverse");

        /*Создаем объект класса Exception в который передаем результат работы метода, который ожидает что метод readSorting
        вызовет исключение SorterReaderException; final - т.к. его ссылка не должна меняться*/
        final Exception sorterReaderException = assertThrows(SorterReaderException.class, () -> reader.readSorting(processedTransport));

        /*Проверяем что объект sorterReaderException не null*/
        Assertions.assertNotNull(sorterReaderException, "sorterReaderException is null");
        /*Проверяем что сообщение фактического исключения идентично ожидаемому*/
        assertEquals("Ошибка чтения сортировки для транспорта", sorterReaderException.getMessage());
    }
}