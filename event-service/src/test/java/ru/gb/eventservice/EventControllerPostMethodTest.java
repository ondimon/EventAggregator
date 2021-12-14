package ru.gb.eventservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.eventservice.dto.EventDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/test_data.sql")
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
@Transactional
class EventControllerPostMethodTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldAddNewEvent() throws Exception {
        List<String> tags = Arrays.asList("tag1", "tag2");
        EventDto eventDto = EventDto.builder()
                .name("New test")
                .description("new description")
                .link("https://mapstruct.org")
                .dateStart(LocalDateTime.now())
                .dateEnd(LocalDateTime.now())
                .tags(tags)
                .build();


        mockMvc.perform(
                post("/events").content(objectMapper.writeValueAsString(eventDto)).contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.error").value(""))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void shouldAddNewEvent_name_and_link() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("New test")
                .link("https://mapstruct.org")
                .build();

        mockMvc.perform(
                        post("/events").content(objectMapper.writeValueAsString(eventDto)).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.result").value(true))
                .andExpect(jsonPath("$.error").value(""))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void shouldNotAddNewEvent_blank_name() throws Exception {
        EventDto eventDto = EventDto.builder()
                .name("")
                .link("https://mapstruct.org")
                .build();

        mockMvc.perform(
                        post("/events").content(objectMapper.writeValueAsString(eventDto)).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(jsonPath("$.error").isNotEmpty());
    }
}
