package ru.gb.eventservice.mapper;


import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ru.gb.eventservice.domain.Event;
import ru.gb.eventservice.dto.EventDto;
import java.util.List;


@Mapper(imports = {ru.gb.eventservice.domain.Tag.class, java.util.stream.Collectors.class})
public abstract class EventMapper {
    public static final EventMapper MAPPER = Mappers.getMapper(EventMapper.class);

    @Mapping(target="tags", expression= "java( event.getTags().stream().map(Tag::getName).collect(Collectors.toList()) )")
    public abstract EventDto fromEvent(Event event);

    @InheritInverseConfiguration
    @Mapping(target = "tags", ignore = true)
    public abstract Event fromEventDto(EventDto eventDto);

    @AfterMapping
    protected void mapTags(EventDto eventDto, @MappingTarget Event result) {
        List<String> tags = eventDto.getTags();
        if (tags == null) {
            return;
        }
        tags.forEach(result::addTag);
    }
}
