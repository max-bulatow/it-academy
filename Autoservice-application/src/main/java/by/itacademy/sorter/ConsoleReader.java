package by.itacademy.sorter;

import java.util.Scanner;

public class ConsoleReader {
    //Статическая константа Scanner для хранения введенной информации в консоль
    private static final Scanner SCANNER = new Scanner(System.in);

    //Метод для чтения информации с консоли
    public String nextLine() {
        return SCANNER.nextLine();
    }

}
