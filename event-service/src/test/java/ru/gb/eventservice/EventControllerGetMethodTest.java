package ru.gb.eventservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.eventservice.exception.EventNotFoundException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Sql(value = "/test_data_before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/test_data_after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestPropertySource(properties = {
        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect"
})
class EventControllerGetMethodTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllEvents() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"))
                .andExpect(jsonPath("$.[1].name").value("test 2"));
    }

    @Test
    void shouldReturnOneEventFilterToDate() throws Exception {
        mockMvc.perform(get("/events?todate=2021-12-08"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"));
    }

    @Test
    void shouldReturnZeroEventFilterToDate() throws Exception {
        mockMvc.perform(get("/events?todate=2021-12-07"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnTwoEventFilterToDate() throws Exception {
        mockMvc.perform(get("/events?todate=2021-12-11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"))
                .andExpect(jsonPath("$.[1].name").value("test 2"));
    }

    @Test
    void shouldReturnOneEventFilterFromDate() throws Exception {
        mockMvc.perform(get("/events?fromdate=2021-12-11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 2"));
    }

    @Test
    void shouldReturnZeroEventFilterFromDate() throws Exception {
        mockMvc.perform(get("/events?fromdate=2021-12-12"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void shouldReturnTwoEventFilterFromDate() throws Exception {
        mockMvc.perform(get("/events?fromdate=2021-12-08"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"))
                .andExpect(jsonPath("$.[1].name").value("test 2"));
    }

    @Test
    void shouldReturnTwoEventFilterFromDateToDate() throws Exception {
        mockMvc.perform(get("/events?fromdate=2021-12-08&todate=2021-12-11"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"))
                .andExpect(jsonPath("$.[1].name").value("test 2"));
    }

    @Test
    void shouldReturnZeroEventFilterFromDateUpdate() throws Exception {
        mockMvc.perform(get("/events?fromdateupdate=2022-01-02T15:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
    @Test
    void shouldReturnTwoEventFilterFromDateUpdate() throws Exception {
        mockMvc.perform(get("/events?fromdateupdate=2021-12-13T15:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("test 1"))
                .andExpect(jsonPath("$.[1].name").value("test 2"));
    }

    @Test
    void shouldReturnEventById() throws Exception {
        mockMvc.perform(get("/events/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("test 1"));
    }

    @Test
    void shouldReturnEventNotFoundExceptionById() throws Exception {
        mockMvc.perform(get("/events/{id}", 3))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(EventNotFoundException.class));
    }
}
