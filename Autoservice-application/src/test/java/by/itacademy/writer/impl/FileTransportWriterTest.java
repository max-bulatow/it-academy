package by.itacademy.writer.impl;

import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import by.itacademy.writer.TransportWriterException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FileTransportWriterTest {

    private final List<Transport> validTransportList = new ArrayList<>();
    private final List<Transport> invalidTransportList = new ArrayList<>();
    private final File validTransportFile = Path.of("src", "test", "resources", "validTransportFile.json").toFile();
    private final File validTransportActual = Path.of("src", "test", "resources", "validTransportActual.json").toFile();
    private final File invalidTransportFile = Path.of("src", "test", "resources", "invalidTransportFile.json").toFile();
    private final File invalidTransportActual = Path.of("src", "test", "resources", "invalidTransportActual.json").toFile();

    @BeforeEach
    void setUp() {
        validTransportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));
        validTransportList.add(new Transport(TransportType.AUTOMOBILE, "BMW M5"));
        validTransportList.add(new Transport(TransportType.MINIBUS, "Sprinter264"));
        invalidTransportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q9!№"));
        invalidTransportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja **"));
    }

    @AfterEach
    void finishTest() {
        invalidTransportFile.delete();
        validTransportFile.delete();
    }

    @Test
    void testWrite_happyPath() throws TransportWriterException, IOException {
        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        processedTransport.put("validTransport", validTransportList);
        processedTransport.put("invalidTransport", invalidTransportList);

        final var writer = new FileTransportWriter(validTransportFile, invalidTransportFile);
        writer.write(processedTransport);

        Assertions.assertEquals(Files.readAllLines(validTransportFile.toPath()), Files.readAllLines(validTransportActual.toPath()));
        Assertions.assertEquals(Files.readAllLines(invalidTransportFile.toPath()), Files.readAllLines(invalidTransportActual.toPath()));
    }

    @Test
    void testWriter_throwException() throws IOException {
        validTransportFile.createNewFile();
        validTransportFile.setReadOnly();
        invalidTransportFile.createNewFile();
        invalidTransportFile.setReadOnly();

        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        processedTransport.put("validTransport", validTransportList);
        processedTransport.put("invalidTransport", invalidTransportList);

        final var writer = new FileTransportWriter(validTransportFile, invalidTransportFile);
        final var fileWriterException = assertThrows(TransportWriterException.class, () -> writer.write(processedTransport));

        assertNotNull(fileWriterException, "fileWriterException is null");
        assertEquals("Ошибка записи файла", fileWriterException.getMessage());
    }
}