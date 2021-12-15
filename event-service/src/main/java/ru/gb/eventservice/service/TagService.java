package ru.gb.eventservice.service;

import ru.gb.eventservice.domain.Tag;
import java.util.List;

public interface TagService {
    Tag findByName(String name);
    List<Tag> findTagsByName(List<String> names);
    Tag saveOrUpdate(Tag tag);
}
