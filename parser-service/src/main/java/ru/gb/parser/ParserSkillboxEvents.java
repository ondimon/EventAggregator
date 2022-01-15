package ru.gb.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserSkillboxEvents extends Parser {

    private static final String url = "https://live.skillbox.ru/";
    private static final String urlPage = "https://live.skillbox.ru/";
    private static final String nameBlockGeneral = "section[class=webinars-section]";
    private static final String nameBlockLink = "a[class=webinar-card button-play webinar-card--default]";
    private static final String nameBlockTitle = "h3[class=webinar-card__title]";

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
            String url = urlPage + eventUrl.attr("href");
            String text = titles.get(i);
            jsonObject = getJson(url,text);
            jsonObjectList.add(jsonObject);
            i++;
        }
        return jsonObjectList;
    }
}
