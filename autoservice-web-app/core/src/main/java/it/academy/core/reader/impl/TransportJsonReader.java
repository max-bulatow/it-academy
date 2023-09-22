package it.academy.core.reader.impl;


import by.itacademy.model.Transport;
import it.academy.core.parser.TransportParser;
import it.academy.core.parser.TransportParserException;
import it.academy.core.reader.TransportReader;
import it.academy.core.reader.TransportReaderException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static it.academy.core.util.Constants.DEFAULT_CHARSET;


public class TransportJsonReader implements TransportReader {
    private final InputStream inputStream;
    private final TransportParser parser;

    public TransportJsonReader(final InputStream inputStream, final TransportParser parser) {
        this.inputStream = inputStream;
        this.parser = parser;
    }

    @Override
    public List<Transport> read() throws TransportReaderException{
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, DEFAULT_CHARSET))) {
            final String content = reader.lines()
                    .reduce("", String::concat);

            return parser.parse(content);
        } catch (final IOException exception) {
            throw new TransportReaderException("Не удалось прочитать содержимое", exception);
        } catch (final TransportParserException exception) {
            throw new TransportReaderException("Не удалось пропарсить контент", exception);
        }
    }
}
