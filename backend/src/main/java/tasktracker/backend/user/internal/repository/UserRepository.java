package tasktracker.backend.user.internal.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasktracker.backend.user.internal.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
