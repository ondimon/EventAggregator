package ru.gb.eventservice.controller.filter;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.repository.specification.EventSpecification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class EventFilter {
    private static final String KEY_FROM_DATE = "fromdate";
    private static final String KEY_TO_DATE = "todate";

    private Specification<Event> spec;

    public EventFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        if (map.containsKey(KEY_FROM_DATE) && !map.get(KEY_FROM_DATE).isEmpty()) {
            LocalDate date = LocalDate.parse(map.get(KEY_FROM_DATE));
            LocalDateTime localDateTime = date.atStartOfDay();
            spec = spec.and(EventSpecification.dataGreaterOrEqualsThan("dateStart", localDateTime));
        }
        if (map.containsKey(KEY_TO_DATE) && !map.get(KEY_TO_DATE).isEmpty()) {
            LocalDate date = LocalDate.parse(map.get(KEY_TO_DATE));
            LocalDateTime localDateTime = date.atTime(23, 59, 59);
            spec = spec.and(EventSpecification.dataLessOrEqualsThan("dateEnd", localDateTime));
        }
    }

}
