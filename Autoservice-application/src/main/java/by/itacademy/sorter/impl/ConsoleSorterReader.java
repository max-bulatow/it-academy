package by.itacademy.sorter.impl;

import by.itacademy.sorter.*;
import by.itacademy.transport.Transport;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.itacademy.validator.impl.TransportValidator.VALID_TRANSPORT;

public class ConsoleSorterReader implements SorterReading {

    /*Создается переменная типа ConsoleReader; private - т.к. будет использоваться только внутри класса
    final - т.к. ее ссылка не должна меняться*/
    private final ConsoleReader consoleReader;

    //Конструктор класса ConsoleSorterReader
    public ConsoleSorterReader(final ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    /*Сообщение которое будет выводиться на экран (меню); private - должно быть доступно только внутри класса
    final - т.к. ее ссылка не должна меняться*/
    private final String SORTING_MESSAGE = "Введите поле для сортировки одно из:"
            + "\n " + Arrays.toString(SortingType.values())
            + "\nи через пробел направление соритировки, одно из:"
            + "\n " + Arrays.toString(SortingDirection.values())
            + "\nили нажмите 'Enter' для завершения ввода";

    /*Реализуем метод readSorting интерфейса SorterReading*/
    @Override
    public Map<String, List<Transport>> readSorting(final Map<String, List<Transport>> processedTransport) throws SorterReaderException {
        /*Создаем HashMap в котором будем хранить введенный тип сортировки и направление
        final - т.к. его ссылка не должна меняться*/
        final Map<SortingType, SortingDirection> sorterMap = new HashMap<>(SortingType.values().length);

        /*Оборачиваем нашу логику в блок try-catch т.к. в процессе работы могут возникать исключения*/
        try {
            /*Созадаем цикл в котором будем обрабатывать введенные значения для сортировки, условие цикла пока размер
            sorterMap меньше чем длина массива значений SortingType т.к. типы сортировок не должны повторяться*/
            while (sorterMap.size() < SortingType.values().length) {
                /*Вывод нашего сообщения (меню) в консоль*/
                System.out.println(SORTING_MESSAGE);

                /*Создается переменная sorter типа String, которой присваивается значение прочитанное с консоли
                final - т.к. ее ссылка не должна меняться*/
                final String sorter = consoleReader.nextLine();

                /*Если введенна в консоль строка пуста, то возвращаем null*/
                if (sorter == null || sorter.isEmpty()) {
                    return null;
                }

                /*Создается массив parts типа String в который мы помещаем введенные нами значения для сортировки разбитые
                на подстроки по разделителю (пробелу) final - т.к. его ссылка не должна меняться*/
                final String[] parts = sorter.split("\\s");
                /*Создается переменная типа SortingType, которой присваивается значение полученное методом valueOf
                enum SoringType, в который мы передаем первую подстроку приведенную к верхнему регистру
                final - т.к. ее ссылка не должна меняться*/
                final SortingType sortingType = SortingType.valueOf(parts[0].toUpperCase());
                /*Аналогично переменной sortingType*/
                final SortingDirection sortingDirection = SortingDirection.valueOf(parts[1].toUpperCase());

                /*Создается переменная типа boolean, которой мы проверяем если ли в sorterMap ключ с таким же значением,
                которое мы уже ввели, final - т.к. после инициализации не должно изменяться*/
                final boolean hasSorter = sorterMap.containsKey(sortingType);

                /*Если ключ с таким значением уже есть, то выводим соощение что такая сортировка уже добавлена
                иначе добавляем ввденные значения в sorterMap*/
                if (hasSorter) {
                    System.out.println("Сортировка " + sortingType + " уже добавлена, введите другую сортировку.");
                } else {
                    sorterMap.put(sortingType, sortingDirection);
                }

                /*Если введенное значение направления сортировки соответствует прямому направлению, то берем коллекцию
                валидного транспорта и применяем к ней сортировку по типу прочитанному с консоли*/
                if (sortingDirection.equals(SortingDirection.FORWARD)) {
                    processedTransport.get(VALID_TRANSPORT).sort(sortingType.getComparator());
                /*Если введенное значение направления сортировки соответствует обратному направлению, то берем коллекцию
                валидного транспорта и применяем к ней сортировку в обратном порядке по типу прочитанному с консоли*/
                } else if (sortingDirection.equals(SortingDirection.REVERSE)) {
                    processedTransport.get(VALID_TRANSPORT).sort(sortingType.getComparator().reversed());
                }

                /*Выводим сообщение о введенном количестве сортировок в консоль*/
                System.out.println("Введено типов сортировок: " + sorterMap.size());
            }
            /*Метод возвращает отсортированную Map processedTransport*/
            return processedTransport;
            /*В случае возникновения ошибки отлавливаем ее в блоке catch и выбрасываем исключение с помощью оператора throw*/
        } catch (final RuntimeException exception) {
            throw new SorterReaderException("Ошибка чтения сортировки для транспорта", exception);
        }
    }
}
