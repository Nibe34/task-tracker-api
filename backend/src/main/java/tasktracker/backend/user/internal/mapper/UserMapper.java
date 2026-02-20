package tasktracker.backend.user.internal.mapper;

import org.mapstruct.Mapper;
import tasktracker.backend.user.internal.domain.User;
import tasktracker.backend.user.internal.dto.UserCreateDto;
import tasktracker.backend.user.internal.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserCreateDto dto);
}
