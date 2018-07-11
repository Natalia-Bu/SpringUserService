package testProject.repository;

import org.springframework.data.repository.CrudRepository;
import testProject.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}