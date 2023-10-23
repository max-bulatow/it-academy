package it.academy.core.sorter.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.TransportType;
import it.academy.core.sorter.SorterReaderException;
import it.academy.core.sorter.SortingReader;
import it.academy.core.transport.TransportContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SorterReaderTest {

    final List<Transport> validTransport = new ArrayList<>();
    final TransportContainer transportContainer = new TransportContainer(validTransport, null);

    @BeforeEach
    void setUp() {
        validTransport.add(new Transport(TransportType.MINIBUS, "Sprinter264"));
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));
        validTransport.add(new Transport(TransportType.AUTOMOBILE, "BMW M5"));
    }

    @Test
    void testReadSorting_happyPath() throws SorterReaderException {
        final String expectedProcessedTransport = "[MINIBUS Sprinter264 30, AUTOMOBILE BMW M5 20, AUTOMOBILE Audi Q7 20]";
        final String sortingContent = "type-forward, model-reverse";

        final SortingReader sortingReader = new SorterReader();
        sortingReader.readSorting(sortingContent, transportContainer);

        Assertions.assertEquals(expectedProcessedTransport, transportContainer.getValidTransport().toString());
    }

    @Test
    void testReadSorting_throwException() {
        final String sortingContent = "ttype-forward, model-reverse";

        final SortingReader sortingReader = new SorterReader();

        final Exception sorterReaderException = assertThrows(SorterReaderException.class, () -> sortingReader.readSorting(sortingContent, transportContainer));

        assertNotNull(sorterReaderException, "sorterReaderException is null");
        assertEquals("Введена не корректная сортировка", sorterReaderException.getMessage());
    }
}