package it.academy.core.validator.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.annotations.Validation;
import it.academy.core.transport.TransportContainer;
import it.academy.core.validator.FieldValidatorException;
import it.academy.core.validator.Validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class TransportValidator implements Validator {

    @Override
    public TransportContainer validateTransport(final List<Transport> transportList) throws FieldValidatorException {

        final List<Transport> validTransportList = new ArrayList<>();
        final List<Transport> invalidTransportList = new ArrayList<>();

        for (final Transport transport : transportList) {
            boolean isValidTransport = false;
            for (final Field field : transport.getClass().getDeclaredFields()) {
                isValidTransport = validate(field, transport);

                if (isValidTransport) {
                    validTransportList.add(transport);
                    break;
                }
            }
            if (!(isValidTransport)) {
                invalidTransportList.add(transport);
            }
        }

        return new TransportContainer(validTransportList, invalidTransportList);
    }

    private boolean validate(final Field field, final Transport transport) throws FieldValidatorException {
        for (final Annotation annotation : field.getDeclaredAnnotations()) {
            if (!(annotation instanceof Validation validation)) {
                continue;
            }

            if (!field.canAccess(transport) && !field.trySetAccessible()) {
                continue;
            }

            try {
                final String modelValueTransport = (String) field.get(transport);
                final Predicate<String> predicate = Pattern.compile(validation.pattern()).asPredicate();

                return predicate.test(modelValueTransport);
            } catch (final IllegalAccessException exception) {
                throw new FieldValidatorException("Не удалось обработать аннотацию", exception);
            }
        }
        return false;
    }
}
