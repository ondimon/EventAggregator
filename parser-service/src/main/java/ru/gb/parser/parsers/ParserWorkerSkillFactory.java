package ru.gb.parser.parsers;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.gb.parser.domain.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserWorkerSkillFactory extends ParserWorker {

    private static final String LIST_EVENTS = "https://blog.skillfactory.ru";
    private static final String BASE_URL = "https://blog.skillfactory.ru";
    private static final String NAME_BLOCK_GENERAL = "div[class=swiper-wrapper]";
    private static final String NAME_BLOCK_EVENT_ITEM = "div[class=swiper-slide]";
    private static final String NAME_BLOCK_LINK = "a";
    private static final String NAME_BLOCK_TITLE = "div[class=card-slider__title]";

    @Override
    public List<Event> getEvents() throws IOException {

        List<Event> events = new ArrayList<>();
        Document page = getPageByLink(LIST_EVENTS);
        Element blockGeneral = page.select(NAME_BLOCK_GENERAL).first();
        Elements webinarsItems = blockGeneral.select(NAME_BLOCK_EVENT_ITEM);

        for (Element webinarItem : webinarsItems) {
            String title = webinarItem.select(NAME_BLOCK_TITLE).first().text();
            String url = String.format("%s%s",
                    BASE_URL,
                    webinarItem.select(NAME_BLOCK_LINK).first().attr(HREF_ATTR));
            Event event = new Event();
            event.setName(title);
            event.setLink(url);
            events.add(event);
        }
        return events;
    }
}
