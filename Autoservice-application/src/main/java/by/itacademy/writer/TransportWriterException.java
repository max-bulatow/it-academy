package by.itacademy.writer;

public class TransportWriterException extends Exception {
    /*Класс TransportWriterException наследуется от класса Exception
    В конструктор передаются неизменяемые (final - т.к их ссылки не должны меняться) переменные message и cause */
    public TransportWriterException(final String message, final Throwable cause) {
        //Вызывается конструктор базового класса Exception, в который передаются параметры message и cause
        super(message, cause);
    }
}
