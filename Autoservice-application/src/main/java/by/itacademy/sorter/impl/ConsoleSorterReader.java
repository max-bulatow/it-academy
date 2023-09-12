package by.itacademy.sorter.impl;

import by.itacademy.sorter.*;
import by.itacademy.transport.Transport;

import java.util.*;

import static by.itacademy.validator.impl.TransportValidator.VALID_TRANSPORT;

public class ConsoleSorterReader implements SorterReading {

    private final ConsoleReader consoleReader;

    public ConsoleSorterReader(final ConsoleReader consoleReader) {
        this.consoleReader = consoleReader;
    }


    private static final String SORTING_MESSAGE = "Введите поле для сортировки одно из:"
            + "\n " + Arrays.toString(SortingType.values())
            + "\nи через пробел направление соритировки, одно из:"
            + "\n " + Arrays.toString(SortingDirection.values())
            + "\nили нажмите 'Enter' для завершения ввода";


    @Override
    public Map<String, List<Transport>> readSorting(final Map<String, List<Transport>> processedTransport) throws SorterReaderException {
        final Map<SortingType, SortingDirection> sorterMap = new HashMap<>();

        try {
            while (sorterMap.size() < SortingType.values().length) {
                System.out.println(SORTING_MESSAGE);

                final String sorter = consoleReader.nextLine();

                if (sorter == null || sorter.isEmpty()) {
                    return null;
                }

                final String[] parts = sorter.split("\\s");
                final SortingType sortingType = SortingType.valueOf(parts[0].toUpperCase());
                final SortingDirection sortingDirection = SortingDirection.valueOf(parts[1].toUpperCase());

                final boolean hasSorter = sorterMap.containsKey(sortingType);

                if (hasSorter) {
                    System.out.println("Сортировка " + sortingType + " уже добавлена, введите другую сортировку.");
                } else {
                    sorterMap.put(sortingType, sortingDirection);
                }

                if (sortingDirection.equals(SortingDirection.FORWARD)) {
                    processedTransport.get(VALID_TRANSPORT).sort(sortingType.getComparator());
                } else {
                    processedTransport.get(VALID_TRANSPORT).sort(sortingType.getComparator().reversed());
                }

                System.out.println("Введено типов сортировок: " + sorterMap.size());
            }
            return processedTransport;
        } catch (final RuntimeException exception) {
            throw new SorterReaderException("Ошибка чтения сортировки для транспорта", exception);
        }
    }
}
