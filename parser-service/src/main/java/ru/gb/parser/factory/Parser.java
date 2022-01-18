package ru.gb.parser.factory;
import ru.gb.parser.domain.Event;
import ru.gb.parser.parsers.ParserWorker;

import java.io.IOException;
import java.util.List;

public abstract class Parser {

    ParserWorker worker;

    Parser() {
        worker = getParserWorker();
    }

    public List<Event> parse() throws IOException {
       List<Event> events = worker.getEvents();
       return events;
    }

    protected abstract ParserWorker getParserWorker();
}
