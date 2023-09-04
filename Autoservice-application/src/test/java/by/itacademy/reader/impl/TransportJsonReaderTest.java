package by.itacademy.reader.impl;

import by.itacademy.parser.TransportParserException;
import by.itacademy.parser.impl.TransportJsonParser;
import by.itacademy.reader.TransportReaderException;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransportJsonReaderTest {

    private static final String TEST_FILE_NAME = "testTransport.json";

    private static final String TEST_FILE_CONTENT = "[  {    \"type\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }]";

    @Test
    void testRead_happyPath() throws TransportParserException, TransportReaderException {

        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"));
        transportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));

        final var parser = Mockito.mock(TransportJsonParser.class);
        Mockito.when(parser.parse(TEST_FILE_CONTENT)).thenReturn(transportList);

        final var reader = new TransportJsonReader(TEST_FILE_NAME, parser);
        final var actualTransport = reader.read();

        Assertions.assertEquals(actualTransport, transportList);
        Mockito.verify(parser).parse(TEST_FILE_CONTENT);

    }

    @Test
    void testRead_parserThrowException() throws TransportParserException {
        final var parser = Mockito.mock(TransportJsonParser.class);
        Mockito.doThrow(TransportParserException.class).when(parser).parse(TEST_FILE_CONTENT);

        final var reader = new TransportJsonReader(TEST_FILE_NAME, parser);
        final var error = Assertions.assertThrowsExactly(TransportReaderException.class, reader::read);

        Assertions.assertEquals(error.getMessage(), "Ошибка обработки содержимого файла [testTransport.json]");
        Mockito.verify(parser).parse(TEST_FILE_CONTENT);
    }
}