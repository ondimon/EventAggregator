package ru.gb.parser;

public class ParserFactory {

    public Parser getParser (ParserTypes type) {
        Parser toReturn = null;
        switch (type) {
            case GBEVENTS:
                toReturn = new ParserGbEvents();
                break;
            case GBPOSTS:
                toReturn = new ParserGbPosts();
                break;
            case SKILLBOX_EVENTS:
                toReturn = new ParserSkillboxEvents();
                break;
            case SKILLFACTORY_EVENTS:
                toReturn = new ParserSkillFactoryEvents();
                break;
            default:
                throw new IllegalArgumentException("Wrong parser type:" + type);
        }
        return toReturn;
    }
}
