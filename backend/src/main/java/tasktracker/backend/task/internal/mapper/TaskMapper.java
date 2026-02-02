package tasktracker.backend.task.internal.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tasktracker.backend.task.internal.dto.TaskCreateDto;
import tasktracker.backend.task.internal.dto.TaskResponseDto;
import tasktracker.backend.task.internal.domain.Task;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDto toDto(Task task);


    Task toEntity(TaskCreateDto taskCreateDto);


    List<TaskResponseDto> toDto(List<Task> tasks);


    void updateEntityFromDto(TaskCreateDto taskCreateDto, @MappingTarget Task task);


}
