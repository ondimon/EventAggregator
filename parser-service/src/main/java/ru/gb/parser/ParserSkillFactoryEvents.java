package ru.gb.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSkillFactoryEvents extends Parser {

    private static final String url = "https://skillfactory.ru/events";
    private static final String nameBlockGeneral = "div[class=t774__container t774__container_mobile-grid]";
    private static final String nameBlockLink = "a[class = t774__btn-text t-btntext t-btntext_sm]";
    private static final String nameBlockTitle = "div[class=t774__title t-name t-name_xs]";

    public List<JSONObject> parse() throws IOException, JSONException {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> jsonObjectList = new ArrayList<>();
        Elements eventsTitle = getBlockElements(url,nameBlockGeneral, nameBlockTitle);
        List<String> titles = new ArrayList<>();
        for (Element eventTitle: eventsTitle) {
            String text = eventTitle.text();
            titles.add(text);
        }
        Elements eventsUrl = getBlockElements(url,nameBlockGeneral, nameBlockLink);
        int i = 0;
        for (Element eventUrl: eventsUrl) {
            String url = eventUrl.attr("href");
            String text = titles.get(i);
            jsonObject = getJson(url, text);
            jsonObjectList.add(jsonObject);
            i++;
        }
        return jsonObjectList;
    }
}
