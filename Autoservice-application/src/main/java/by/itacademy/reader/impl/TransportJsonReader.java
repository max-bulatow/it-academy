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

    /*Поле fileName типа String для хранения имени файла, который будем читать
    private - т.к. должно быть доступно только внутри класса TransportJsonReader, final - т.к. после инициализации не должно изменяться*/
    private final String fileName;
    /*Поле parser типа TransportParser для хранения объекта класса TransportParser
    private - т.к. должно быть доступно только внутри класса TransportJsonReader, final - т.к. после инициализации не должно изменяться*/
    private final TransportParser parser;

    //Конструктор класcа TransportJsonReader с полями fileName и parser
    public TransportJsonReader(final String fileName, final TransportParser parser) {
        this.fileName = fileName;
        this.parser = parser;
    }

    //Реализуем метод read интерфейса TransportReader
    @Override
    public List<Transport> read() throws TransportReaderException {
        /*Объявляется переменная reader, которой присвается значение полученное методом getReader,
        final - т.к. после инициализации не должна изменяться. Оборачиваем в конструкцию try-catch т.к. могут возникать
        ошибки во время выполнения метода*/
        try (final var reader = getReader()) {
            /*Объявляется переменная content в которую заносятся значения полученные в результате чтения всех строк в виде
            потока и преобразования всех элементов стрима (reduce) в один объект с помощью объединения строк
            если не будет возращено никакого значения, то вернется null*/
            final var content = reader.lines()
                    .reduce(String::concat)
                    .orElse(null);

            //метод возвращает "пропарсенный контент"
            return parser.parse(content);
        } //В случае возникновения ошибки обрабатываем ее в блоке catch и выбрасываем исключение с помощью опретора throw
        catch (final IOException | TransportParserException exception) {
            final var errorMessage = "Ошибка обработки содержимого файла [%s]".formatted(fileName);
            throw new TransportReaderException(errorMessage, exception);
        }
    }

    //Метод, для чтения содержимого файла (fileName) из папки resources, который возвращает BufferedReader
    private BufferedReader getReader() throws TransportReaderException {
        //Создается переменная in, которой присваивается поток, содержащий сожержимое файла (fileName)
        final var in = getClass().getClassLoader().getResourceAsStream(fileName);
        //Если он не равен null, то возвращаем BufferedReader с содержимым прочитанного файла
        if (in != null) {
            return new BufferedReader(new InputStreamReader(in));
        }
        //При возникновении ошибок, выбрасываем исключение
        throw new TransportReaderException("Файл [%s] пустой".formatted(fileName));
    }

}
