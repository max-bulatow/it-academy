package by.itacademy;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.impl.TransportJsonParser;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.impl.TransportJsonReader;
import by.itacademy.sorter.ConsoleReader;
import by.itacademy.sorter.SorterReading;
import by.itacademy.sorter.impl.ConsoleSorterReader;
import by.itacademy.transport.Transport;
import by.itacademy.validator.Validator;
import by.itacademy.validator.impl.TransportValidator;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.impl.FileTransportWriter;

import java.io.File;
import java.util.List;
import java.util.Map;

import static by.itacademy.validator.impl.TransportValidator.INVALID_TRANSPORT;
import static by.itacademy.validator.impl.TransportValidator.VALID_TRANSPORT;

public class AutoserviceApplication {

    public static void main(String[] args) {

        System.out.println("Старт программы автосервис!");

        try {
            final TransportParser parser = new TransportJsonParser();
            final TransportReader reader = new TransportJsonReader("transport.json", parser);

            final List<Transport> transportList = reader.read();

            final Validator validator = new TransportValidator();
            final Map<String, List<Transport>> processedTransport = validator.mapTransportList(VALID_TRANSPORT, INVALID_TRANSPORT, transportList);

            final ConsoleReader consoleReader = new ConsoleReader();
            final SorterReading sorterReading = new ConsoleSorterReader(consoleReader);
            sorterReading.readSorting(processedTransport);

            final TransportWriter fileTransportWriter = new FileTransportWriter(new File("processed-transport.json"),
                    new File("invalid-transport.json"));
            final List<TransportDestination> destinations = fileTransportWriter.write(processedTransport);
            destinations.forEach(System.out::println);

        } catch (final Exception exception) {
            System.err.println("Конец программы автосервис: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
