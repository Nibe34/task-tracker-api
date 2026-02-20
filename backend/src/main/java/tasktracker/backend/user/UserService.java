package tasktracker.backend.user;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tasktracker.backend.common.exception.InvalidSortDirectionException;
import tasktracker.backend.common.exception.InvalidSortFieldException;
import tasktracker.backend.common.exception.EmptyPatchException;
import tasktracker.backend.user.internal.domain.User;
import tasktracker.backend.user.internal.dto.UserCreateDto;
import tasktracker.backend.user.internal.dto.UserFilterDto;
import tasktracker.backend.user.internal.dto.UserUpdateDto;
import tasktracker.backend.user.internal.exception.UserNotFoundException;
import tasktracker.backend.user.internal.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "id", "username", "email", "createdAt"
    );


    @Transactional
    public User save(UserCreateDto userCreateDto) {
        User user = User.create(userCreateDto.username(), userCreateDto.email());
        return userRepository.save(user);
    }


    public Page<User> searchUsers(UserFilterDto filter, int page, int size, String sortDir, String sortField) {

        if (!ALLOWED_SORT_FIELDS.contains(sortField)) {
            throw new InvalidSortFieldException(sortField, ALLOWED_SORT_FIELDS);
        }

        Sort.Direction sortDirection;
        try {
            sortDirection = Sort.Direction.fromString(sortDir);
        } catch (IllegalArgumentException e) {
            throw new InvalidSortDirectionException(sortDir);
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortField));

        if (filter == null) {
            return userRepository.findAll(pageable);
        }

        String usernameParam = null, emailParam = null;
        if (filter.username() != null && !filter.username().isBlank()) {
            usernameParam = "%" + filter.username().toLowerCase() + "%";
        }

        if (filter.email() != null && !filter.email().isBlank()) {
            emailParam = "%" + filter.email().toLowerCase() + "%";
        }

        return userRepository.searchUsers(usernameParam, emailParam, pageable);
    }


    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @Transactional
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }


    @Transactional
    public User patchUser(Long id, UserUpdateDto userUpdateDto) {
        User user = findById(id);

        if (userUpdateDto.username() == null && userUpdateDto.email() == null) {
            throw new EmptyPatchException();
        }

        if (userUpdateDto.username() != null) {
            user.changeUsername(userUpdateDto.username());
        }

        if (userUpdateDto.email() != null) {
            user.changeEmail(userUpdateDto.email());
        }

        return userRepository.save(user);
    }
}
