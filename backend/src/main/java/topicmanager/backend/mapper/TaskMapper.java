package topicmanager.backend.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import topicmanager.backend.dto.TaskCreateDto;
import topicmanager.backend.dto.TaskResponseDto;
import topicmanager.backend.model.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDto toDto(Task task);


    Task toEntity(TaskCreateDto taskCreateDto);


    List<TaskResponseDto> toDto(List<Task> tasks);


    void updateEntityFromDto(TaskCreateDto taskCreateDto, @MappingTarget Task task);


}
