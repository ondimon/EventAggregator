package ru.gb.eventservice.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.eventservice.domain.Event;
import java.time.LocalDateTime;

public class EventSpecification {

    private EventSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Event> dataGreaterOrEqualsThan(String nameData, LocalDateTime dateTime) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(nameData), dateTime);
    }

    public static Specification<Event> dataLessOrEqualsThan(String nameData, LocalDateTime dateTime) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(nameData), dateTime);
    }
}
