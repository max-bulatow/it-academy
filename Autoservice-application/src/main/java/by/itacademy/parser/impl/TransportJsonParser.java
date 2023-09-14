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

    //Реализуем метод parse интерфейса TransportParser
    @Override
    public List<Transport> parse(String content) throws TransportParserException {
        /*Создается коллекция List, типизируемая классом Transport, для хранения создаваемых объектов класса Transport
        final - т.к. ее ссылка не должна меняться */
        final List<Transport> transportList = new ArrayList<>();
        /*Создается массив Json объектов, в который мы помещаем наш content
        final - т.к. его ссылка не должна меняться*/
        final JSONArray jsonTransportArray = new JSONArray(content);

        //Оборачиваем нашу логику в блок try-catch т.к. в процессе работы с Json могут вызываться исключения
        try {
            /*В цикле for-each пробегаемся по массиву jsonTransportArray, выделяя объекты нашего транспорта
            final - т.к. ссылка на итерационную переменную transport не должна меняться при прохождении по массиву*/
            for (final Object transport : jsonTransportArray) {
                /*Создается переменная типа JsonObject, которой присваивается значение переменной transport,
                которая приводиться к типу JsonObject, final - т.к. ее ссылка не должна меняться*/
                final JSONObject transportJsonObject = (JSONObject) transport;

                /*Создается переменная type типа String, которой присваивается значение полученное методом getString
                transportJsonObject по ключу "type", final - т.к. ее ссылка не должна меняться */
                final String type = transportJsonObject.getString("type");
                /*Создается переменная transportType типа transportType, которой присваивается значение полученное методом
                valueOf enum TransportType, в который мы передаем переменную type приведенную к верхнему регистру,
                чтобы вернуть элемент Enum TransportType c названием равным переменной type
                final - т.к. ее ссылка не должна меняться*/
                final TransportType transportType = TransportType.valueOf(type.toUpperCase());
                /*Аналогично переменной type*/
                final String model = transportJsonObject.getString("model");

                /*Добавляем в коллекцию transportList новый объект класса Transport c полями transportType и model*/
                transportList.add(new Transport(transportType, model));
            }
            //Метод возвращает коллекцию transportList
            return transportList;
            //В случае возникновения ошибки отлавливаем ее в блоке catch и выбрасываем исключение с помощью оператора throw
        } catch (final JSONException exception) {
            throw new TransportParserException("Ошибка парсинга содержимого JSON файла", exception);
        }
    }
}
