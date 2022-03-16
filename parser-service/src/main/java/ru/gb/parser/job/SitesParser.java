package ru.gb.parser.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.parser.domain.Event;
import ru.gb.parser.domain.ParsedLink;
import ru.gb.parser.factory.Parser;
import ru.gb.parser.factory.ParserGeekBrains;
import ru.gb.parser.factory.ParserSkillBox;
import ru.gb.parser.factory.ParserSkillFactory;
import ru.gb.parser.service.EventService;
import ru.gb.parser.service.ParsedLinksService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
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

    @Scheduled(fixedDelay = 300000)
    public void parseSitesJob()  {
        Parser[] parsers = {new ParserSkillBox(),
                            new ParserSkillFactory(),
                            new ParserGeekBrains()};
        parseSites(parsers);
    }

    public void parseSites(Parser[] parsers) {
        for (Parser parser: parsers) {
            String parserName = parser.getClass().getSimpleName();
            log.info("Start parsing {}", parserName);
            try{
                parseSite(parser);
            }catch (Exception e) {
                log.error("parser {}: {}", parserName, e.getMessage());
            }finally {
                log.info("Stop parsing {}", parserName);
            }
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
