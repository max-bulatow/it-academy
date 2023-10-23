package it.academy.core.reader;

import by.itacademy.model.Transport;
import it.academy.core.parser.TransportParserException;

import java.util.List;

public interface TransportReader {
    List<Transport> read() throws TransportReaderException, TransportParserException;
}
