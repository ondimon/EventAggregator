package ru.gb.eventservice.domain;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "events_tag")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event;

    @Column(name = "name")
    private String name;
}
