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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.itacademy.validator.impl.TransportValidator.INVALID_TRANSPORT;
import static by.itacademy.validator.impl.TransportValidator.VALID_TRANSPORT;

public class FileTransportWriter implements TransportWriter {

    private final File processedTransportFile;
    private final File invalidTransportFile;

    public FileTransportWriter(File processedTransportFile, File invalidTransportFile) {
        this.processedTransportFile = processedTransportFile;
        this.invalidTransportFile = invalidTransportFile;
    }

    @Override
    public List<TransportDestination> write(final Map<String, List<Transport>> processedTransport) throws TransportWriterException {
        final TransportDestination invalidDestination = new TransportDestination("JSON файл транспорта с ошибками",
                invalidTransportFile);
        final TransportDestination processedDestination = new TransportDestination("JSON файл продиагностированного транспорта",
                processedTransportFile);

        writer(processedTransport.get(VALID_TRANSPORT), true);
        writer(processedTransport.get(INVALID_TRANSPORT), false);

        return List.of(processedDestination, invalidDestination);
    }

    private void writer(final List<Transport> transportList, final boolean isValid) throws TransportWriterException {
        final File outFile = isValid ? processedTransportFile : invalidTransportFile;

        final List<String> invalidJsonObjectList = new ArrayList<>(transportList.size());
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile, StandardCharsets.UTF_8))) {

            for (Transport transport : transportList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("type", transport.getTransportType());
                jsonObject.put("model", transport.getModel());

                if (isValid) {
                    jsonObject.put("price", transport.getPrice());
                }
                invalidJsonObjectList.add(jsonObject.toString(4));
            }
            bufferedWriter.write(invalidJsonObjectList.toString());
        } catch (final IOException exception) {
            throw new TransportWriterException("Ошибка записи файла", exception);
        }
    }
}
