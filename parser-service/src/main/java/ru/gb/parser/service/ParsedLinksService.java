package ru.gb.parser.service;

import org.springframework.stereotype.Service;
import ru.gb.parser.domain.ParsedLink;


public interface ParsedLinksService {
    ParsedLink save(ParsedLink parsedLink);
    boolean linkIsParsed(String link);
    ParsedLink findByLink(String link);
}
