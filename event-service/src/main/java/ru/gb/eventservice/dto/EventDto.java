package ru.gb.eventservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String link;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private List<String> tags;

}
