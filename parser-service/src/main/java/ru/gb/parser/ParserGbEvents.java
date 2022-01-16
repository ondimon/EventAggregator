package ru.gb.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserGbEvents extends Parser {

    private static final String url = "https://gb.ru/events";
    private static final String nameBlockGeneral = "div[class=gb-future-events]";
    private static final String nameBlock = "h3[class=gb-event-info__item gb-event-info__title]";
    private static final String nameBlockLink = "a";

    public List<JSONObject> parse() throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        Elements events = getBlockElements(url,nameBlockGeneral, nameBlock, nameBlockLink);
        for (Element event: events) {
            String url = event.attr("href");
            String text = event.text();
            jsonObject = getJson(url,text);
            jsonObjectList.add(jsonObject);
        }
        return jsonObjectList;
    }
}
