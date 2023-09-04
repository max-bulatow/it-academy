package by.itacademy.validator;

import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface validator {
    Map <String, List<Transport>> mapTransportList (String validTransport, String invalidTransport, List<Transport> transportList);
}
