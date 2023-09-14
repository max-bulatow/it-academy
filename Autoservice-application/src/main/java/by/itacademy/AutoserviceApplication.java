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

    public static void main(final String[] args) {

        System.out.println("Старт программы автосервис!");

        /*Оборачиваем логику программы в блок try-catch т.к. в процессе работы могут возникать ошибки*/
        try {
            /*Создаем объект класса TransportParser; final - т.к. его ссылка не должна меняться*/
            final TransportParser parser = new TransportJsonParser();
            /*Создаем объект класса TransportReader и передаем ему в конструктор имя файла с транспортом для обработки и
            объект parser класса TransportParser; final - т.к. его ссылка не должна меняться*/
            final TransportReader reader = new TransportJsonReader("transport.json", parser);

            /*Создаем коллекцию List, типизируемую классом Transport в которую помещаем результат работы метода read объекта
            reader; final - т.к. ее ссылка не должна меняться*/
            final List<Transport> transportList = reader.read();

            /*Созадаем объект класса TransportValidator; final - т.к. его ссылка не должна меняться*/
            final Validator validator = new TransportValidator();
            /*Создаем Map в который передаем результат работы метода mapTransportList объекта validator
            final - т.к. его ссылка не должна меняться*/
            final Map<String, List<Transport>> processedTransport = validator.mapTransportList(VALID_TRANSPORT, INVALID_TRANSPORT, transportList);

            /*Создаем объект класса ConsoleReader; final - т.к. его ссылка не должна меняться*/
            final ConsoleReader consoleReader = new ConsoleReader();
            /*Создаем объект класса ConsoleSorterReader и передаем в его конструктор объект consoleReader
            final - т.к. его ссылка не должна меняться*/
            final SorterReading sorterReading = new ConsoleSorterReader(consoleReader);
            /*Вызываем метод readSorting у объекта sorterReading и передаем ему в качестве аргумента Map processedTransport*/
            sorterReading.readSorting(processedTransport);

            /*Создаем объект класса FileTransportWriter и передаем в его конструктор файлы с путями для валидного и невалидного транспорта
            final - т.к. его ссылка не должна меняться*/
            final TransportWriter fileTransportWriter = new FileTransportWriter(new File("processed-transport.json"),
                    new File("invalid-transport.json"));
            /*Создается коллекция List типизируемая классом TransportDestination в которую мы передаем результат работы метода
            write объекта fileTransportWriter, который принимает в качестве аргумента Map processedTransport;
            final - т.к. его ссылка не должна меняться*/
            final List<TransportDestination> destinations = fileTransportWriter.write(processedTransport);
            /*Вывод сожержимого коллекции в консоль*/
            destinations.forEach(System.out::println);

            /*В случае возникновения ошибок обрабытываем их в блоке catch*/
        } catch (final Exception exception) {
            System.err.println("Конец программы автосервис: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
