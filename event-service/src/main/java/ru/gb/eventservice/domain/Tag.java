package ru.gb.eventservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "events_tag")
@Data
@NoArgsConstructor
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_event")
    @NotNull
    private Event event;

    @Column(name = "name")
    @NotNull
    private String name;

    public Tag(Event event, String name) {
        this.event = event;
        this.name = name;
    }
}
