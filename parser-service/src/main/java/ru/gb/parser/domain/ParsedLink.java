package ru.gb.parser.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "parsed_links")
public class ParsedLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link")
    @NotBlank
    private String link;

    @Column(name = "date")
    private LocalDateTime date;


}
