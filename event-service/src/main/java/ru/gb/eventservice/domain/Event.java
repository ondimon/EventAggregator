package ru.gb.eventservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "link")
    private String link;

    @Column(name = "date_start")
    private LocalDateTime date_start;

    @Column(name = "date_end")
    private LocalDateTime date_end;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL)
    private List<Tag> tags;
}
