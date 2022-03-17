package ru.gb.parser.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.gb.parser.domain.Event;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ParserWorkerSkillBoxTest {

    private static final String BASE_URL = "https://live.skillbox.ru";

    @Spy
    ParserWorkerSkillBox parserWorkerSkillBox;

    @Test
    void parseSkillBoxTest() throws IOException {
        File file = new File(
                getClass().getClassLoader().getResource("html/SkillBox.html").getFile()
        );
        Document document = Jsoup.parse(file, "UTF-8");
        Mockito.doReturn(document).when(parserWorkerSkillBox).getPageByLink(BASE_URL);

        List<Event> eventsList = parserWorkerSkillBox.getEvents();
        assertEquals(2, eventsList.size());

        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-01T00:00:00");
        Event event = eventsList.get(0);
        assertEquals("test name", event.getName());
        assertEquals(BASE_URL + "/test", event.getLink());
        assertEquals(localDateTime, event.getDateStart());
        assertEquals(localDateTime, event.getDateEnd());

        event = eventsList.get(1);
        assertEquals("test name 2", event.getName());
        assertEquals(BASE_URL + "/test2", event.getLink());
        assertEquals(null, event.getDateStart());
        assertEquals(null, event.getDateEnd());
    }
}