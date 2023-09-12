package by.itacademy.reader;

public class TransportReaderException extends Exception {

    /*Класс TransportReaderException наследуется от класса Exception
    В конструктор передаются неизменяемая (final) переменная message*/
    public TransportReaderException(final String message) {
        //Вызывается конструктор базового класса Exception, в который передается параметр message
        super(message);
    }

    /*Конструктор класса TransportReaderException, в который передаются неизменяемые (final) переменные
     message и cause*/
    public TransportReaderException(final String message, final Throwable cause) {
        //Вызывается конструктор базового класса Exception, в который передаются параметры message и cause
        super(message, cause);
    }

}
