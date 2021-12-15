package ru.gb.eventservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.eventservice.domain.Tag;
import ru.gb.eventservice.repository.TagRepository;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{
    private TagRepository tagRepository;

    private TagServiceImpl() {
    }

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public List<Tag> findTagsByName(List<String> names) {
        return tagRepository.findTagsByNameIn(names);
    }

    @Override
    public Tag saveOrUpdate(Tag tag) {
        return tagRepository.save(tag);
    }
}
