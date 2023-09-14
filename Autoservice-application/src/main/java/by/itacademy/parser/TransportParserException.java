package by.itacademy.parser;

public class TransportParserException extends Exception {
    /*Класс TransportParserException наследуется от класса Exception
    В конструктор передаются неизменяемые (final - т.к. их ссылка не должна меняться) переменные message и cause */
    public TransportParserException(final String message, final Throwable cause) {
        //Вызывается конструктор базового класса Exception, в который передаются параметры message и cause
        super(message, cause);
    }
}
