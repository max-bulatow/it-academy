package by.itacademy.validator;

import by.itacademy.transport.Transport;

import java.lang.reflect.Field;

public interface FieldValidator {
    /*Метод интерфейса FieldValidator, который принимает в качестве аргументов поле класса и экземпляр класса
    и возвращает значение типа boolean*/
    boolean validate (final Field field, final Transport transport) throws FieldValidatorException;
}
