package ru.gb.eventservice.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.domain.Tag;
import ru.gb.eventservice.dto.EventDto;
import ru.gb.eventservice.service.TagService;
import java.util.List;

@Mapper(componentModel="spring", imports = {ru.gb.eventservice.domain.Tag.class, java.util.stream.Collectors.class})
public abstract class EventMapper {
    @Autowired
    protected TagService tagService;

    @Mapping(target="tags", expression= "java( event.getTags().stream().map(Tag::getName).collect(Collectors.toList()) )")
    public abstract EventDto fromEvent(Event event);

    @InheritInverseConfiguration
    @Mapping(target = "tags", ignore = true)
    public abstract Event fromEventDto(EventDto eventDto);

    @AfterMapping
    protected void mapTags(EventDto eventDto, @MappingTarget Event result) {
        List<String> tagsFromDto = eventDto.getTags();
        if (tagsFromDto == null) {
            return;
        }
        List<Tag> tags = tagService.findTagsByName(tagsFromDto);

        for (Tag tag : tags) {
            result.addTag(tag);
            tagsFromDto.remove(tag.getName());
        }

        for (String s : tagsFromDto) {
            result.addTag(s);
        }
    }
}
