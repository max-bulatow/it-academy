package by.itacademy.parser.impl;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.transport.Transport;
import by.itacademy.transport.TransportType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransportJsonParser implements TransportParser {

    @Override
    public List<Transport> parse(String content) throws TransportParserException {
        final List<Transport> transportList = new ArrayList<>();
        final JSONArray jsonTransportArray = new JSONArray(content);

        try {
            for (final Object transport : jsonTransportArray) {
                final JSONObject transportJsonObject = (JSONObject) transport;

                final String type = transportJsonObject.getString("type");
                final TransportType transportType = TransportType.valueOf(type.toUpperCase());
                final String model = transportJsonObject.getString("model");

                transportList.add(new Transport(transportType, model));
            }
            return transportList;
        } catch (final JSONException exception) {
            throw new TransportParserException("Ошибка парсинга содержимого JSON файла", exception);
        }
    }
}
