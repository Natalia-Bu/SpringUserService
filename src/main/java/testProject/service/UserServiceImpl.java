package testProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testProject.model.User;
import testProject.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User addUser(final User user) {
        return this.repository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public void deleteUser(final User user) {
        this.repository.delete(user);
    }
}
