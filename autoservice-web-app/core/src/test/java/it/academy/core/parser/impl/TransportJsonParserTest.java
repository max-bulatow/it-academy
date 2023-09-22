package it.academy.core.parser.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.TransportType;
import it.academy.core.parser.TransportParserException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportJsonParserTest {

    @Test
    void testParse_happyPath() throws TransportParserException {
        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja ZX-14"));
        transportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));

        final String content = "[  {    \"type\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }  ]";

        final TransportJsonParser parser = new TransportJsonParser();

        final List<Transport> transportListParsing = parser.parse(content);

        assertNotNull(transportListParsing, "transport list is null");
        assertEquals(transportListParsing, transportList);
    }

    @Test
    void testParse_parserThrowException() {
        final String content = "[  {    \"ttype\": \"motorcycle\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"automobile\",    \"model\": \"Audi Q7\"  }  ]";
        final TransportJsonParser parser = new TransportJsonParser();

        final Exception transportParserException = assertThrows(TransportParserException.class, () -> parser.parse(content));

        assertNotNull(transportParserException, "transportParserException is null");
        assertEquals("Ошибка парсинга содержимого JSON файла", transportParserException.getMessage());
    }
}