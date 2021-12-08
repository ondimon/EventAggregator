package ru.gb.eventservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.eventservice.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
