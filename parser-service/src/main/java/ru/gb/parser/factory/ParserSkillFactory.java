package ru.gb.parser.factory;

import ru.gb.parser.parsers.ParserWorker;
import ru.gb.parser.parsers.ParserWorkerSkillFactory;

public class ParserSkillFactory extends Parser {

    @Override
    protected ParserWorker getParserWorker() {
        return new ParserWorkerSkillFactory();
    }
}
