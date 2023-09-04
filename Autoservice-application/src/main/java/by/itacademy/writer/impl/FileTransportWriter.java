package by.itacademy.writer.impl;

import by.itacademy.transport.Transport;
import by.itacademy.writer.TransportDestination;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.TransportWriterException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static by.itacademy.AutoserviceApplication.INVALID_TRANSPORT;
import static by.itacademy.AutoserviceApplication.VALID_TRANSPORT;

public class FileTransportWriter implements TransportWriter {

    private final File processedTransportFile;
    private final File invalidTransportFile;

    public FileTransportWriter(File processedTransportFile, File invalidTransportFile) {
        this.processedTransportFile = processedTransportFile;
        this.invalidTransportFile = invalidTransportFile;
    }

    @Override
    public List<TransportDestination> write(final Map<String, List<Transport>> processedTransport, Comparator<Transport> comparator) throws TransportWriterException {
        final TransportDestination invalidDestination = new TransportDestination("JSON файл транспорта с ошибками",
                invalidTransportFile);
        final TransportDestination processedDestination = new TransportDestination("JSON файл продиагностированного транспорта",
                processedTransportFile);

        try (final BufferedWriter validTransportWriter = new BufferedWriter(new FileWriter(processedTransportFile));
             final BufferedWriter invalidTransportWriter = new BufferedWriter(new FileWriter(invalidTransportFile))) {

            processedTransport.get(VALID_TRANSPORT).sort(comparator);

            final JSONObject validTransportJsonObject = new JSONObject();
            final List<String> validTransportJsonList = new ArrayList<>();
            int index;
            for (index = 0; index < processedTransport.get(VALID_TRANSPORT).size(); index++) {

                validTransportJsonObject.put("type", processedTransport.get(VALID_TRANSPORT).get(index).getTransportType());
                validTransportJsonObject.put("model", processedTransport.get(VALID_TRANSPORT).get(index).getModel());
                validTransportJsonObject.put("price", processedTransport.get(VALID_TRANSPORT).get(index).getTransportType().getPrice());
                validTransportJsonList.add(validTransportJsonObject.toString(4));

            }

            validTransportWriter.write(validTransportJsonList.toString());
            validTransportWriter.flush();

            final JSONObject invalidTransportJsonObject = new JSONObject();
            final List<String> invalidTransportJsonList = new ArrayList<>();
            int indeks;
            for (indeks = 0; indeks < processedTransport.get(INVALID_TRANSPORT).size(); indeks++) {

                invalidTransportJsonObject.put("type", processedTransport.get(INVALID_TRANSPORT).get(indeks).getTransportType());
                invalidTransportJsonObject.put("model", processedTransport.get(INVALID_TRANSPORT).get(indeks).getModel());
                invalidTransportJsonList.add(invalidTransportJsonObject.toString(4));

            }
            invalidTransportWriter.write(invalidTransportJsonList.toString());
            invalidTransportWriter.flush();
        } catch (final IOException exception) {
            throw new TransportWriterException("Ошибка записи файла", exception);
        }
        return List.of(processedDestination, invalidDestination);
    }
}
