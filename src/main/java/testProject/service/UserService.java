package testProject.service;

import testProject.model.User;

public interface UserService {
    User addUser(User user);

    User getByEmail(String email);

    void deleteUser(User user);
}
