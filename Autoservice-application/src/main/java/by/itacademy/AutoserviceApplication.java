package by.itacademy;

import by.itacademy.parser.impl.TransportJsonParser;
import by.itacademy.reader.impl.TransportJsonReader;
import by.itacademy.sorter.SorterReading;
import by.itacademy.sorter.impl.ConsoleSorterReader;
import by.itacademy.transport.Transport;
import by.itacademy.validator.impl.TransportValidator;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.impl.FileTransportWriter;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class AutoserviceApplication {
    public static final String VALID_TRANSPORT = "validTransport";
    public static final String INVALID_TRANSPORT = "invalidTransport";

    public static void main(String[] args) {

        System.out.println("Старт программы автосервис!");

        try {
            final TransportJsonParser parser = new TransportJsonParser();
            final TransportJsonReader reader = new TransportJsonReader("transport.json", parser);

            final List<Transport> transportList = reader.read();

            final TransportValidator validator = new TransportValidator();
            final Map<String, List<Transport>> processedTransport = validator.mapTransportList(VALID_TRANSPORT, INVALID_TRANSPORT, transportList);

            final SorterReading sorterReading = new ConsoleSorterReader();
            final Comparator<Transport> comparator = sorterReading.readSorting();

            final FileTransportWriter fileTransportWriter = new FileTransportWriter(new File("processed-transport.json"),
                    new File("invalid-transport.json"));
            final List<TransportDestination> destinations = fileTransportWriter.write(processedTransport, comparator);
            destinations.forEach(System.out::println);

        } catch (final Exception exception) {
            System.err.println("Конец программы автосервис: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
