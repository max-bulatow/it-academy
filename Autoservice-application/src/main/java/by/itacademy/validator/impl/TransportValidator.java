package by.itacademy.validator.impl;

import by.itacademy.transport.Transport;
import by.itacademy.validator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TransportValidator implements Validator {
    private static final Pattern MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$");

    @Override
    public Map<String, List<Transport>> mapTransportList(String validTransport, String invalidTransport, List<Transport> transportList) {

        final Map<String, List<Transport>> processedTransport = new HashMap<>();
        final List<Transport> validTransportList = new ArrayList<>();
        final List<Transport> invalidTransportList = new ArrayList<>();

        for (final Transport transport : transportList) {
            if (MODEL_VALIDATOR.matcher(transport.getModel()).matches()) {
                validTransportList.add(transport);
            } else {
                invalidTransportList.add(transport);
            }
        }
        processedTransport.put(validTransport, validTransportList);
        processedTransport.put(invalidTransport, invalidTransportList);

        return processedTransport;
    }

}
