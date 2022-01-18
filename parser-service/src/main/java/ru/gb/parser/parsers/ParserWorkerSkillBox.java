package ru.gb.parser.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.gb.parser.domain.Event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParserWorkerSkillBox extends ParserWorker{
    private static final String LIST_EVENTS = "https://live.skillbox.ru";
    private static final String BASE_URL = "https://live.skillbox.ru";
    private static final String NAME_BLOCK_GENERAL = "section[class=webinars-section]";
    private static final String NAME_BLOCK_EVENT_ITEM = "li[class=webinars__item]";
    private static final String NAME_BLOCK_LINK = "a[class=webinar-card button-play webinar-card--default]";
    private static final String NAME_BLOCK_TITLE = "h3[class=webinar-card__title]";
    private static final String NAME_BLOCK_DATE = "span[class=webinar-card__date]";

    @Override
    public List<Event> getEvents() throws IOException {
        List<Event> events = new ArrayList<>();
        Document page =  getPageByLink(LIST_EVENTS);
        Element blockGeneral = page.select(NAME_BLOCK_GENERAL).first();
        Elements webinarsItems = blockGeneral.select(NAME_BLOCK_EVENT_ITEM);
        for (Element webinarItem: webinarsItems) {
            String title = webinarItem.select(NAME_BLOCK_TITLE).first().text();
            String url = BASE_URL + webinarItem.select(NAME_BLOCK_LINK).first().attr("href");
            String date = webinarItem.select(NAME_BLOCK_DATE).first().text();
            Event event = new Event();
            event.setName(title);
            event.setLink(url);
            events.add(event);

            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("dd.MM.yyyy");
            Date docDate;
            try {
                docDate = format.parse(date);
                event.setDateStart(docDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                event.setDateEnd(event.getDateStart());
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        return events;
    }
}
