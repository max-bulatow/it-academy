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

    /*Поле типа File для хранения ссылки на файл обработанного транспорта; final - т.к. его ссылка не должна меняться*/
    private final File processedTransportFile;
    /*Поле типа File для хранения ссылки на файл невалидного транспорта; final - т.к. его ссылка не должна меняться*/
    private final File invalidTransportFile;

    /*Конструктор класса FileTransportWriter*/
    public FileTransportWriter(File processedTransportFile, File invalidTransportFile) {
        this.processedTransportFile = processedTransportFile;
        this.invalidTransportFile = invalidTransportFile;
    }

    /*Реализуем метод write интерфейса TransportWriter*/
    @Override
    public List<TransportDestination> write(final Map<String, List<Transport>> processedTransport) throws TransportWriterException {
        /*Создаем объект класса TransportDestination для невалидного транспорта; final - т.к. его ссылка не должна меняться*/
        final TransportDestination invalidDestination = new TransportDestination("JSON файл транспорта с ошибками",
                invalidTransportFile);
        /*Аналогично для валидного транспорта*/
        final TransportDestination processedDestination = new TransportDestination("JSON файл продиагностированного транспорта",
                processedTransportFile);

        /*Вызываем метод writer и передаем в него коллекции валидного и невалидного транспорта*/
        writer(processedTransport.get(VALID_TRANSPORT), true);
        writer(processedTransport.get(INVALID_TRANSPORT), false);

        /*Метод write возвращает список записанных файлов*/
        return List.of(processedDestination, invalidDestination);
    }

    /*Создается метод для записи информации в файл, который принимает в качестве аргумента коллекцию List, типизируемую
    классом Transport и boolean переменную указывающую валидный транспорт или нет; private - т.к. будет исользоваться
    только внутри класса; Передаваемые аргументы final - т.к. во время работы метода их ссылки не должны меняться*/
    private void writer(final List<Transport> transportList, final boolean isValid) throws TransportWriterException {
        /*Создается переменная типа File в которой храниться ссылка на файл для записи валидного или невалидного транспорта
        final - т.к. ссылка не должна меняться*/
        final File outFile = isValid ? processedTransportFile : invalidTransportFile;
        /*Создается коллекция List типизируемая классом String; final - т.к. ее ссылка не должна меняться*/
        final List<String> JsonObjectList = new ArrayList<>(transportList.size());
        /*Используем try-with-resources для записи файлов*/
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile, StandardCharsets.UTF_8))) {

            /*В цикле for-each пробегаемся по коллекции transportList c помощью итерационной переменной transport
            final - т.к. ссылка на итерационную переменную transport не должна меняться при прохождении по коллекции*/
            for (final Transport transport : transportList) {
                /*Создается переменная типа JSONObject; final - т.к. ее ссылка не должна меняться*/
                final JSONObject jsonObject = new JSONObject();
                /*Помещаем в jsonObject тип транспорта и модель с ключами type и model соответственно*/
                jsonObject.put("type", transport.getTransportType());
                jsonObject.put("model", transport.getModel());

                /*Если транспорт валиден, то помещаем в jsonObject цену с ключом price и добавляем его в коллекцию для записи
                иначе добавляем jsonObject в коллекцию для записи*/
                if (isValid) {
                    jsonObject.put("price", transport.getPrice());
                    JsonObjectList.add(jsonObject.toString(4));
                } else {
                    JsonObjectList.add(jsonObject.toString(4));
                }
            }
            /*Записываем коллекцию транспорта в файл*/
            bufferedWriter.write(JsonObjectList.toString());
            /*В случае возникновения ошибки отлавливаем ее в блоке catch и выбрасываем исключение с помощью оператора throw*/
        } catch (final IOException exception) {
            throw new TransportWriterException("Ошибка записи файла", exception);
        }
    }
}
