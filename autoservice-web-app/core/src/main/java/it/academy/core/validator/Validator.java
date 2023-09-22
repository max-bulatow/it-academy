package it.academy.core.validator;

import by.itacademy.model.Transport;
import it.academy.core.transport.TransportContainer;

import java.util.List;
import java.util.Map;

public interface Validator {
    TransportContainer validateTransport(List<Transport> transportList) throws FieldValidatorException;
}
