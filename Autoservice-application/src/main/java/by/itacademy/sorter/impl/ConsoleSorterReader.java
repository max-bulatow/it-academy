package by.itacademy.sorter.impl;

import by.itacademy.sorter.*;
import by.itacademy.transport.Transport;

import java.util.*;

public class ConsoleSorterReader implements SorterReading {

    private static final String SORTING_MESSAGE = "Введите поле для сортировки одно из:"
            + "\n " + Arrays.toString(SortingType.values())
            + "\nи через пробел направление соритировки, одно из:"
            + "\n " + Arrays.toString(SortingDirection.values())
            + "\nили нажмите 'Enter' для завершения ввода";

    @Override
    public Comparator<Transport> readSorting() throws SorterReaderException {
        try (final Scanner scanner = new Scanner(System.in)) {
            final List<Sorter> sorterList = new ArrayList<>(SortingType.values().length);

            while (sorterList.size() < SortingType.values().length) {
                final Sorter sorter = readSorting(scanner);
                if (sorter == null) {
                    break;
                }

                final SortingType sortingType = sorter.getSortingType();
                final boolean hasSorter = sorterList.stream().anyMatch(s -> s.getSortingType().equals(sortingType));

                if (hasSorter) {
                    System.out.println("Сортировка " + sortingType + " уже добавлена, введите другую сортировку.");
                } else {
                    sorterList.add(sorter);
                }
            }

            System.out.println("Введено типов сортировок: " + sorterList.size());

            return sorterList.stream()
                    .map(Sorter::getComparator)
                    .reduce((t1, t2) -> 0, Comparator::thenComparing);
        } catch (final RuntimeException exception) {
            throw new SorterReaderException("Ошибка чтения сортровки для транспорта", exception);
        }
    }

    private static Sorter readSorting(final Scanner scanner) {
        System.out.println(SORTING_MESSAGE);
        final String sorter = scanner.nextLine();

        if (sorter == null || sorter.isEmpty()) {
            return null;
        }

        final String[] parts = sorter.split("\\s");
        final SortingType sortingType = SortingType.valueOf(parts[0].toUpperCase());
        final SortingDirection sortingDirection = SortingDirection.valueOf(parts[1].toUpperCase());

        return new Sorter(sortingType, sortingDirection);
    }

}
