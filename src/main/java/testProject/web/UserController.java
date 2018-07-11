package testProject.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import testProject.model.User;
import testProject.service.UserService;
import testProject.web.forms.Error;
import testProject.web.forms.Result;
import testProject.web.forms.Success;

@RestController
public class UserController {

    private final UserService users;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(final UserService users) {
        this.users = users;
    }

    /**
     * Saves User to UserRepository if email is unique
     * and request body is ok
     * @param user request body describing user
     * @return response: status (OK or BAD_REQUEST), body (json User or string error)
     * @see User
     */
    @PostMapping("/")
    public Result addUser(@RequestBody User user) {
        if (this.users.getByEmail(user.getEmail()) != null) {
            return new Error("There is an account with that email address:" + user.getEmail(), HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return new Success<User>(this.users.addUser(user));
        } catch (Exception ex) {
            return new Error(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Deletes User from UserRepository
     * @param email path variable. Email of user to delete
     * @return response: status (OK or NOT_FOUND), body (was deleted or string error)
     */
    @DeleteMapping("/{email}")
    public Result deleteUser(@PathVariable String email) {
        final Result result;
        User user = this.users.getByEmail(email);
        if (user == null) {
            result = new Error(String.format("User with email %s not found", email), HttpStatus.NOT_FOUND);
        } else {
            this.users.deleteUser(user);
            result = new Success<String>("User " + user.getFirstName() + " " + user.getSecondName() + " was deleted.");
        }
        return result;
    }

    /**
     * Finds User by email
     * @param email path variable. Email to find by
     * @return response: status (OK or NOT_FOUND), body (json User or string error)
     * @see User
     */
    @GetMapping("/{email}")
    public Result findByEmail(@PathVariable String email) {
        final Result result;
        User user = this.users.getByEmail(email);
        if (user != null) {
            return new Success<User>(user);
        } else {
            result = new Error(String.format("User with email %s not found", email), HttpStatus.NOT_FOUND);
        }
        return result;
    }

}
