package by.itacademy.writer;

import java.io.File;

public class TransportDestination {
    /*Поле типа String в котором будет храниться описание записанного файла; private - будет использоваться только внутри
    класса; final - т.к. его ссылка не должна меняться*/
    private final String description;
    /*Поле типа File для создания файла в котром будут храниться JSON объекты транспорта; private - будет использоваться
    только внутри класса; final - т.к. его ссылка не должна меняться*/
    private final File file;

    /*Конструктор класса TransportDestination*/
    public TransportDestination(final String description, final File file) {
        this.description = description;
        this.file = file;
    }

    /*Переопределяем метод toString для вывода информации в нужном нам виде*/
    @Override
    public String toString() {
        return "Файл: " + file.getAbsolutePath() + " - " + description;
    }
}
