package ru.gb.eventservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.eventservice.domain.Tag;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
    List<Tag> findTagsByNameIn(List<String> names);
}
