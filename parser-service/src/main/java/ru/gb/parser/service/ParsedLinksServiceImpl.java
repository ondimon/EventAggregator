package ru.gb.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.parser.domain.ParsedLink;
import ru.gb.parser.repository.ParsedLinksRepository;

@Service
public class ParsedLinksServiceImpl implements ParsedLinksService{

    private ParsedLinksRepository parsedLinksRepository;

    @Autowired
    public void setParsedLinksRepository(ParsedLinksRepository parsedLinksRepository) {
        this.parsedLinksRepository = parsedLinksRepository;
    }

    @Override
    public ParsedLink save(ParsedLink parsedLink) {
        return parsedLinksRepository.save(parsedLink);
    }

    @Override
    public boolean linkIsParsed(String link) {
        ParsedLink parsedLink = findByLink(link);
        return parsedLink != null;
    }

    @Override
    public ParsedLink findByLink(String link) {
        return parsedLinksRepository.findByLink(link);
    }
}
