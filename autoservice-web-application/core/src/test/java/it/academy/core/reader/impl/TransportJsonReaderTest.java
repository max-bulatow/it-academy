package it.academy.core.reader.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.TransportType;
import it.academy.core.parser.TransportParserException;
import it.academy.core.parser.impl.TransportJsonParser;
import it.academy.core.reader.TransportReaderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static it.academy.core.parser.impl.TransportJsonParserTest.content;

class TransportJsonReaderTest {

    @Test
    void testRead_happyPath() throws TransportParserException, TransportReaderException {
        final var expectedTransportList = new ArrayList<Transport>();
        expectedTransportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"));
        expectedTransportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));

        final var parser = Mockito.mock(TransportJsonParser.class);
        Mockito.when(parser.parse(content)).thenReturn(expectedTransportList);

        final var reader = new TransportJsonReader(content, parser);
        final var actualTransportList = reader.read();

        Assertions.assertEquals(actualTransportList, expectedTransportList);
        Mockito.verify(parser).parse(content);
        Mockito.verifyNoMoreInteractions(parser);
    }

    @Test
    void testRead_parserThrowException() throws TransportParserException {
        final var parser = Mockito.mock(TransportJsonParser.class);
        Mockito.doThrow(TransportParserException.class).when(parser).parse(content);

        final var reader = new TransportJsonReader(content, parser);
        final var actualError = Assertions.assertThrowsExactly(TransportReaderException.class, reader::read);

        Assertions.assertEquals(actualError.getMessage(), "Не удалось пропарсить контент");
        Mockito.verify(parser).parse(content);
        Mockito.verifyNoMoreInteractions(parser);
    }
}