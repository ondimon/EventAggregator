package ru.gb.parser;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/events")
public class ParserService {

    @Autowired
    ParserFactory factory;

    @PostMapping(
            value = "/", consumes = "application/json", produces = "application/json")
    public List<JSONObject> parseGbEvents() throws IOException, JSONException {
        Parser parserGbEvents = factory.getParser(ParserTypes.GBEVENTS);
        return parserGbEvents.parse();
    }

    @PostMapping(
            value = "/", consumes = "application/json", produces = "application/json")
    public List<JSONObject> parseGbPosts() throws IOException, JSONException {
        Parser parserGbPosts = factory.getParser(ParserTypes.GBPOSTS);
        return parserGbPosts.parse();
    }

    @PostMapping(
            value = "/", consumes = "application/json", produces = "application/json")
    public List<JSONObject> parseSkillboxEvents() throws IOException, JSONException {
        Parser parserSkillboxEvents = factory.getParser(ParserTypes.SKILLBOX_EVENTS);
        return parserSkillboxEvents.parse();
    }

    @PostMapping(
            value = "/", consumes = "application/json", produces = "application/json")
    public List<JSONObject> parseSkillFactoryEvents() throws IOException, JSONException {
        Parser parserSkillFactoryEvents = factory.getParser(ParserTypes.SKILLFACTORY_EVENTS);
        return parserSkillFactoryEvents.parse();
    }
}
