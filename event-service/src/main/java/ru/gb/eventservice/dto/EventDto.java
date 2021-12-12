package ru.gb.eventservice.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private String link;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private List<String> tags;

}
