package it.academy.core.reader.impl;


import by.itacademy.model.Transport;
import it.academy.core.parser.TransportParser;
import it.academy.core.parser.TransportParserException;
import it.academy.core.reader.TransportReader;
import it.academy.core.reader.TransportReaderException;

import java.util.List;


public class TransportJsonReader implements TransportReader {
    private final String content;
    private final TransportParser parser;

    public TransportJsonReader(final String content, final TransportParser parser) {
        this.content = content;
        this.parser = parser;
    }

    @Override
    public List<Transport> read() throws TransportReaderException {
        try {
            return parser.parse(content);
        } catch (final TransportParserException exception) {
            throw new TransportReaderException("Не удалось пропарсить контент", exception);
        }
    }
}
