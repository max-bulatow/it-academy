package it.academy.core.parser.impl;

import by.itacademy.model.Transport;
import by.itacademy.model.TransportType;

import it.academy.core.parser.TransportParser;
import it.academy.core.parser.TransportParserException;
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
