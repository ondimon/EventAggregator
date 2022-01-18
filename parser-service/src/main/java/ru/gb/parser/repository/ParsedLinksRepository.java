package ru.gb.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.parser.domain.ParsedLink;

@Repository
public interface ParsedLinksRepository extends JpaRepository<ParsedLink, Long> {
    ParsedLink findByLink(String link);
}
