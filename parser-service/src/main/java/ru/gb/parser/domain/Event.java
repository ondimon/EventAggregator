package ru.gb.parser.domain;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Event {
    private String name;
    private String description;
    private String link;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

}
