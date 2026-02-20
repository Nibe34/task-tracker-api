package tasktracker.backend.user.internal.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tasktracker.backend.user.internal.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u " +
            "WHERE " +
            ":username IS NULL OR LOWER(u.username) LIKE :username " +
            "AND " +
            ":email IS NULL OR LOWER(u.email) LIKE :email"
    )
    Page<User> searchUsers(@Param("username") String username,
                           @Param("email") String email,
                           Pageable pageable);
}
