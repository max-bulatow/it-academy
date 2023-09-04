package by.itacademy.reader.impl;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderException;
import by.itacademy.transport.Transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TransportJsonReader implements TransportReader {

    private final String fileName;
    private final TransportParser parser;

    public TransportJsonReader(final String fileName, final TransportParser parser) {
        this.fileName = fileName;
        this.parser = parser;
    }

    @Override
    public List<Transport> read() throws TransportReaderException {
        try (final var reader = getReader()) {
            final var content = reader.lines()
                    .reduce(String::concat)
                    .orElse(null);

            return parser.parse(content);
        } catch (final IOException | TransportParserException exception) {
            final var errorMessage = "Ошибка обработки содержимого файла [%s]".formatted(fileName);
            throw new TransportReaderException(errorMessage, exception);
        }
    }

    private BufferedReader getReader() throws TransportReaderException {
        final var in = getClass().getClassLoader().getResourceAsStream(fileName);
        if (in != null) {
            return new BufferedReader(new InputStreamReader(in));
        }
        throw new TransportReaderException("Файл [%s] пустой".formatted(fileName));
    }

}
