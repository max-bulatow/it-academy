package by.itacademy.sorter;

public class SorterReaderException extends Exception {
    /*Класс SorterReaderException наследуется от класса Exception
    В конструктор передаются неизменяемые (final - т.к. их ссылки не должны меняться) переменные message и cause */
    public SorterReaderException(final String message, final Throwable cause) {
        //Вызывается конструктор базового класса Exception, в который передаются параметры message и cause
        super(message, cause);
    }
}
