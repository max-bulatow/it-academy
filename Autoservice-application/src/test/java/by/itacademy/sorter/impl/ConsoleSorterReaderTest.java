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

import static org.junit.jupiter.api.Assertions.*;

class ConsoleSorterReaderTest {

    private final Map<String, List<Transport>> processedTransport = new HashMap<>();
    private final String VALID_TRANSPORT = "validTransport";

    @BeforeEach
    void setUp() {
        final List<Transport> validTransport = new ArrayList<>();
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "BMW M5"));
        validTransport.add(new Transport(TransportType.MINIBUS, "Sprinter264"));

        processedTransport.put(VALID_TRANSPORT, validTransport);
    }

    @Test
    void readSorting_happyPath() throws SorterReaderException {
        final String expected = "[MINIBUS Sprinter264 30, AUTOMOBILE Audi Q7 20, AUTOMOBILE BMW M5 20]";
        final ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        final ConsoleSorterReader reader = new ConsoleSorterReader(consoleReader);
        Mockito.when(consoleReader.nextLine()).thenReturn("model forward").thenReturn("type forward").thenReturn("price reverse");

        final Map <String, List <Transport>> actualSorter = reader.readSorting(processedTransport);
        final String actual = actualSorter.get(VALID_TRANSPORT).toString();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(consoleReader, Mockito.times(3)).nextLine();
        Mockito.verifyNoMoreInteractions(consoleReader);
    }

    @Test
    void readSorting_throwException() {
        final ConsoleReader consoleReader = Mockito.mock(ConsoleReader.class);
        final ConsoleSorterReader reader = new ConsoleSorterReader(consoleReader);
        Mockito.when(consoleReader.nextLine()).thenReturn("modell forward").thenReturn("type forward").thenReturn("price reverse");

        final Exception sorterReaderException = assertThrows(SorterReaderException.class, () -> reader.readSorting(processedTransport));

        Assertions.assertNotNull(sorterReaderException, "sorterReaderException is null");
        assertEquals("Ошибка чтения сортировки для транспорта", sorterReaderException.getMessage());
    }
}