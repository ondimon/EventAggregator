package ru.gb.parser.factory;

import ru.gb.parser.annotation.SiteParser;
import ru.gb.parser.parsers.ParserWorker;
import ru.gb.parser.parsers.ParserWorkerSkillBox;

@SiteParser
public class ParserSkillBox extends Parser{

    @Override
    protected ParserWorker getParserWorker() {
        return new ParserWorkerSkillBox();
    }
}
