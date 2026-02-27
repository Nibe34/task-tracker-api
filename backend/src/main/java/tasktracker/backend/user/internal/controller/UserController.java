package tasktracker.backend.user.internal.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tasktracker.backend.user.UserService;
import tasktracker.backend.user.internal.domain.User;
import tasktracker.backend.user.internal.dto.UserCreateDto;
import tasktracker.backend.user.internal.dto.UserFilterDto;
import tasktracker.backend.user.internal.dto.UserResponseDto;
import tasktracker.backend.user.internal.dto.UserUpdateDto;
import tasktracker.backend.user.internal.mapper.UserMapper;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping()
    public UserResponseDto createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        User savedUser = userService.save(userCreateDto);
        return userMapper.toDto(savedUser);
    }




    @GetMapping()
    public Page<UserResponseDto> getAllUsers(
            @ModelAttribute
            UserFilterDto filter,

            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "Page number must be 0 or greater")
            int page,

            @RequestParam(defaultValue = "5")
            @Min(value = 5, message = "Page size must be at least 1")
            @Max(value = 20, message = "Page size must mot exceed 20")
            int size,

            @RequestParam(defaultValue = "ASC")
            String sortDir,
            @RequestParam(defaultValue = "id")
            String sortField
    ) {
        Page<User> userPage = userService.searchUsers(filter, page, size, sortDir, sortField);
        return userPage.map(userMapper::toDto);
    }




    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }




    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }




    @PatchMapping("/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateDto userUpdateDto) {
        User updatedUser = userService.patchUser(id, userUpdateDto);
        return userMapper.toDto(updatedUser);
    }
}
