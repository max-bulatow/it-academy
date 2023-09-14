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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileTransportWriterTest {

    /*Создаем коллекцию List типизируемую классом Transport для хранения валидного транспорта;
    private - т.к. будет использоваться только внутри класса
    final - т.к. ее ссылка не должна меняться*/
    private final List<Transport> validTransportList = new ArrayList<>();
    /*Создаем коллекцию List типизируемую классом Transport для хранения невалидного транспорта;
    private - т.к. будет использоваться только внутри класса
    final - т.к. ее ссылка не должна меняться*/
    private final List<Transport> invalidTransportList = new ArrayList<>();
    /*Создаем переменную типа File в которую передаем путь создаваемого файла
    private - т.к. будет использоваться только внутри класса
    final - т.к. ее ссылка не должна меняться*/
    private final File validTransportFile = Path.of("src", "test", "resources", "validTransportFile.json").toFile();
    private final File validTransportActual = Path.of("src", "test", "resources", "validTransportActual.json").toFile();
    private final File invalidTransportFile = Path.of("src", "test", "resources", "invalidTransportFile.json").toFile();
    private final File invalidTransportActual = Path.of("src", "test", "resources", "invalidTransportActual.json").toFile();

    /*Действия который будут выполняться перед каждым тестом*/
    @BeforeEach
    void setUp() {
        /*Добавляем в коллекции валидного и невалидного транспорта объекты класса Transport*/
        validTransportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q7"));
        validTransportList.add(new Transport(TransportType.AUTOMOBILE, "BMW M5"));
        validTransportList.add(new Transport(TransportType.MINIBUS, "Sprinter264"));
        invalidTransportList.add(new Transport(TransportType.AUTOMOBILE, "Audi Q9!№"));
        invalidTransportList.add(new Transport(TransportType.MOTORCYCLE, "Ninja **"));
    }

    /*Действия которые будут выполняться после каждого теста*/
    @AfterEach
    void finishTest() {
        /*Удаляем файлы с ожидаемыми записанными данными */
        invalidTransportFile.delete();
        validTransportFile.delete();
    }

    /*Тест выполнения метода write класса FileTransportWriter*/
    @Test
    void testWrite_happyPath() throws TransportWriterException, IOException {
        /*Создаем HashMap для хранения коллекций валдиного и невалидного транспорта; fianl - т.к. его ссылка не должна меняться*/
        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        /*Кладем коллекции валидного и невалидного транспорта в HashMap*/
        processedTransport.put("validTransport", validTransportList);
        processedTransport.put("invalidTransport", invalidTransportList);

        /*Создаем объект класса FileTransportWriter; final - т.к. его ссылка не должна меняться*/
        final var writer = new FileTransportWriter(validTransportFile, invalidTransportFile);
        writer.write(processedTransport);

        /*Проверяем что содержимое фактического файла идентично ожидаемому для валидного и невалидного транспорта*/
        Assertions.assertEquals(Files.readAllLines(validTransportFile.toPath()), Files.readAllLines(validTransportActual.toPath()));
        Assertions.assertEquals(Files.readAllLines(invalidTransportFile.toPath()), Files.readAllLines(invalidTransportActual.toPath()));
    }

    /*Тест метода на вызывание исключений*/
    @Test
    void testWriter_throwException() throws IOException {
        /*Создаем новые файлы для валидного и невалидного транспорта и устанавливаем для них доступ - только для чтения*/
        validTransportFile.createNewFile();
        validTransportFile.setReadOnly();
        invalidTransportFile.createNewFile();
        invalidTransportFile.setReadOnly();

        /*Создаем HashMap для хранения коллекций валдиного и невалидного транспорта; fianl - т.к. его ссылка не должна меняться*/
        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        /*Кладем коллекции валидного и невалидного транспорта в HashMap*/
        processedTransport.put("validTransport", validTransportList);
        processedTransport.put("invalidTransport", invalidTransportList);

        /*Создаем объект класса FileTransportWriter; final - т.к. его ссылка не должна меняться*/
        final var writer = new FileTransportWriter(validTransportFile, invalidTransportFile);
        /*Создаем объект класса Exception в который передаем резултат работы метода, который ожидает что метод write
        вызовет исключение TransportWriterException; final - т.к. его ссылка не должна меняться*/
        final var fileWriterException = assertThrows(TransportWriterException.class, () -> writer.write(processedTransport));

        /*Проверяем что объект fileWriterException не null*/
        assertNotNull(fileWriterException, "fileWriterException is null");
        /*Проверяем что сообщение фактического исключения идентично ожидаемому*/
        assertEquals("Ошибка записи файла", fileWriterException.getMessage());
    }
}