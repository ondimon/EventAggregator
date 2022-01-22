package ru.gb.parser.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.gb.parser.domain.Event;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public abstract class ParserWorker {

    protected final String HREF_ATTR = "href";

    protected Document getPageByLink(String url) throws IOException {
       return Jsoup.connect(url)
               .userAgent("Mozilla")
               .timeout(5000)
               .referrer("https://google.com")
               .get();
    }

    public abstract List<Event> getEvents() throws IOException;
}
