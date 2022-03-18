package ru.gb.parser.job;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.parser.domain.Event;
import ru.gb.parser.domain.ParsedLink;
import ru.gb.parser.factory.Parser;
import ru.gb.parser.factory.ParserGeekBrains;
import ru.gb.parser.factory.ParserSkillBox;
import ru.gb.parser.factory.ParserSkillFactory;
import ru.gb.parser.service.EventService;
import ru.gb.parser.service.ParsedLinksService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {SitesParser.class, ParserGeekBrains.class, ParserSkillBox.class, ParserSkillFactory.class})
class SitesParserTest {
    @MockBean
    private EventService eventService;

    @MockBean
    private ParsedLinksService parsedLinksService;

    @Autowired
    private SitesParser sitesParser;

    @Test
    void getParsers() {
        List<Parser> parsers = sitesParser.getParsers();
        assertEquals(3, parsers.size());

        List<Class<? extends Parser>> expectedClasses = new ArrayList<>();

        expectedClasses.add(ParserGeekBrains.class);
        expectedClasses.add(ParserSkillBox.class);
        expectedClasses.add(ParserSkillFactory.class);
        boolean allClassInArray = true;
        for (Parser parser: parsers) {
            if (!expectedClasses.contains(parser.getClass())) {
                allClassInArray = false;
                break;
            }
        }
        assertTrue(allClassInArray);
    }

    @Test
    void parseSitesTest() throws IOException {
        Parser parserMock = Mockito.mock(Parser.class);
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setName("test");
        event.setLink("http://test.ru");
        events.add(event);

        Mockito.when(parserMock.parse()).thenReturn(events);
        sitesParser.parseSite(parserMock);
        Mockito.verify(eventService).saveEvent(event);
        Mockito.verify(parsedLinksService).save(Mockito.any(ParsedLink.class));
    }

    @Test
    void parseSitesTestAllLinkParsed() throws IOException {
        Parser parserMock = Mockito.mock(Parser.class);
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setName("test");
        event.setLink("http://test.ru");
        events.add(event);

        Mockito.when(parsedLinksService.linkIsParsed(event.getLink())).thenReturn(true);
        Mockito.when(parserMock.parse()).thenReturn(events);

        sitesParser.parseSite(parserMock);

        Mockito.verify(eventService, Mockito.times(0)).saveEvent(event);
        Mockito.verify(parsedLinksService, Mockito.times(0)).save(Mockito.any(ParsedLink.class));
    }


    @Test
    void parseSites() throws IOException {
        Parser parserMock = Mockito.mock(Parser.class);
        List<Parser> parsers = new ArrayList<>();
        parsers.add(parserMock);
        sitesParser.parseSites(parsers);
        Mockito.verify(parserMock).parse();
    }

    @Test

    void parseSitesException() throws IOException {
        Parser parserMock1 = Mockito.mock(Parser.class);
        Parser parserMock2 = Mockito.mock(Parser.class);
        List<Parser> parsers = new ArrayList<>();
        parsers.add(parserMock1);
        parsers.add(parserMock2);
        Mockito.when(parserMock1.parse()).thenThrow(IOException.class);
        sitesParser.parseSites(parsers);
        Mockito.verify(parserMock2).parse();
    }
}