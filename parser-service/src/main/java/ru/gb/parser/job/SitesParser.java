package ru.gb.parser.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.parser.domain.Event;
import ru.gb.parser.domain.ParsedLink;
import ru.gb.parser.factory.Parser;
import ru.gb.parser.factory.ParserSkillBox;
import ru.gb.parser.service.EventService;
import ru.gb.parser.service.ParsedLinksService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SitesParser {
    ParsedLinksService parsedLinksService;
    EventService eventService;

    @Autowired
    public void setEventService(EventService eventService) {
       this.eventService = eventService;
    }

    @Autowired
    public void setParsedLinksService(ParsedLinksService parsedLinksService) {
        this.parsedLinksService = parsedLinksService;
    }

    @Scheduled(fixedDelay = 60000)
    public void parseSitesJob() throws IOException {
        Parser[] parsers = {new ParserSkillBox()};
        parseSites(parsers);
    }

    public void parseSites(Parser[] parsers) throws IOException {
        for (Parser parser: parsers) {
            parseSite(parser);
        }
    }

    public void parseSite(Parser parser) throws IOException {
        List<Event> events = parser.parse();
        for (Event event : events) {
            if(parsedLinksService.linkIsParsed(event.getLink())) continue;

            eventService.saveEvent(event);

            ParsedLink parsedLink = new ParsedLink();
            parsedLink.setLink(event.getLink());
            parsedLink.setDate(LocalDateTime.now());
            parsedLinksService.save(parsedLink);
        }
    }
}
