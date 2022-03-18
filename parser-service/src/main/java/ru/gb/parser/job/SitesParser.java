package ru.gb.parser.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gb.parser.annotation.SiteParser;
import ru.gb.parser.domain.Event;
import ru.gb.parser.domain.ParsedLink;
import ru.gb.parser.factory.Parser;
import ru.gb.parser.service.EventService;
import ru.gb.parser.service.ParsedLinksService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class SitesParser {
    private final ParsedLinksService parsedLinksService;
    private final EventService eventService;
    private final List<Parser> parsers;

    @Scheduled(fixedDelay = 300000)
    public void parseSitesJob()  {
        parseSites(getParsers());
    }

    public List<Parser> getParsers() {
        return  parsers.stream().filter(
                                        parser -> parser.getClass().isAnnotationPresent(SiteParser.class)
                                    ).collect(Collectors.toList());
    }

    public void parseSites(List<Parser> parsers) {
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
