package by.itacademy.validator.impl;

import by.itacademy.annotations.Validation;
import by.itacademy.transport.Transport;
import by.itacademy.validator.FieldValidator;
import by.itacademy.validator.FieldValidatorException;
import by.itacademy.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TransportValidator implements Validator {

    /*Поле типа String, которое будет использоваться в качестве ключа для Map
    public - должно быть доступно другим классам в других пакетах; static - константа должна быть общая для всего класса
    и вызываться без создания объекта класса; final - т.к. его ссылка не должна меняться*/
    public static final String VALID_TRANSPORT = "validTransport";

    /*Аналогично выше*/
    public static final String INVALID_TRANSPORT = "invalidTransport";

    /*Реализуем метод mapTransportList интерфейса Validator*/
    @Override
    public Map<String, List<Transport>> mapTransportList(final String validTransport, final String invalidTransport, final List<Transport> transportList) throws FieldValidatorException {

        /*Создается HashMap c ключом типа String и значением List, типизируемом классом Transport для хранения
        коллекций валидного и невалидного транспорта; final - т.к. его ссылка не должна меняться*/
        final Map<String, List<Transport>> processedTransport = new HashMap<>(2);
        /*Объявляется коллекция List типизируемая классом Transport для хранения валидного транспорта
        final - т.к. ее ссылка не должна меняться*/
        final List<Transport> validTransportList = new ArrayList<>();
        /*Аналогично для хранения невалидного транспорта*/
        final List<Transport> invalidTransportList = new ArrayList<>();

        /*В цикле for-each пробегаемся по коллекции transportList с помощью итерационной переменной transport
        final - т.к. ссылка на итерационную переменную transport не должна меняться при прохождении по коллекции*/
        for (final Transport transport : transportList) {
            /*Создаем переменную типа boolean и присваиваем ей значение false*/
            boolean isValidTransport = false;
            /*В цикле for-each пробегаемся по всем полям которые получаем у класса нашего объекта transport
            final- т.к. ссылка на итерационнную переменную не должна меняться во время одной итерации цикла*/
            for (final Field field : transport.getClass().getDeclaredFields()) {
                /*Присваиваем нашей переменной результат работы метода validate*/
                isValidTransport = validate(field, transport);

                /*Если true, то заносим наш объект в коллекцию валидного транспорта*/
                if (isValidTransport) {
                    validTransportList.add(transport);
                    break;
                }
            }
            /*Если false, то заносим объект в коллекцию невалидного транспорта*/
            if (!(isValidTransport)) {
                invalidTransportList.add(transport);
            }
        }

        /*Помещаем коллекции валидного и невалидного транспорта в созданную ранее HashMap processedTransport*/
        processedTransport.put(VALID_TRANSPORT, validTransportList);
        processedTransport.put(INVALID_TRANSPORT, invalidTransportList);

        /*Метод возвращает заполненный HashMap processedTransport*/
        return processedTransport;
    }

    /*Реализуем метод validate интерфейса FieldValidator*/
    //@Override
    private boolean validate(final Field field, final Transport transport) throws FieldValidatorException {
        /*В цикле for-each пробегаемся по всем аннотациям поля; final - т.к. ссылка итерационной переменной не должна меняться*/
        for (final Annotation annotation : field.getDeclaredAnnotations()) {
            /*Проверяем что полученная аннотация принадлежит созданной нами аннотации Validation*/
            if (!(annotation instanceof Validation validation)) {
                continue;
            }

            /*Если наше поле недоступно можем ли мы сделать его доступным*/
            if (!field.canAccess(transport) && !field.trySetAccessible()) {
                continue;
            }

            /*Оборачиваем нашу логику в блок try-catch т.к. в процессе работы могут вызываться исключения*/
            try {
                /*Создаем переменную типа String в которой будем хранить значение поля приведенное к типу String
                final - т.к. ее ссылка не должна меняться*/
                final String modelValueTransport = (String) field.get(transport);
                /*Создаем переменную predicate в которую помещаем предикат, который юудет использован для сопоставления
                шаблона полученного из поля аннотациии; final - т.к. ее ссылка не должна меняться */
                final Predicate<String> predicate = Pattern.compile(validation.pattern()).asPredicate();

                /*Проверяем соответсвие нашего значения паттерну*/
                return predicate.test(modelValueTransport);
                /*В случае возникновения ошибки отлавливаем ее в блоке catch и выбрасываем исключение с помощью оператора throw*/
            } catch (final IllegalAccessException exception) {
                throw new FieldValidatorException("Не удалось обработать аннотацию", exception);
            }
        }
        return false;
    }
}
