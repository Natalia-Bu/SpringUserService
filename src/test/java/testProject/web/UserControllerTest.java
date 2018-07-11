package testProject.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import testProject.model.User;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenCreatingUserThenGetUser() {
        User user = new User("Ivan", "Ivanov", Date.valueOf("2000-01-01"), "ivanov@mail.ru", "12345");
        ResponseEntity<User> responseEntity =
                restTemplate.postForEntity("/", user, User.class);
        User responseUser = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user.getEmail(), responseUser.getEmail());
    }

    @Test
    public void whenCreatingUserEmailExistThenException() {
        User user = new User("Ivan", "Ivanov", Date.valueOf("2000-01-01"), "ivanov@mail.ru", "12345");
        User wrongUser = new User("Fedr", "Ivanov", Date.valueOf("1990-01-01"), "ivanov@mail.ru", "23456");
        restTemplate.postForObject("/", user, String.class);
        String rslt = restTemplate.postForObject("/", wrongUser, String.class);
        assertEquals("There is an account with that email address:" + wrongUser.getEmail(), rslt);
    }

    @Test
    public void whenFindingByEmailThenGetUser() {
        User user = new User("Ivan", "Ivanov", Date.valueOf("2000-01-01"), "ivanov@mail.ru", "12345");
        restTemplate.postForEntity("/", user, User.class);
        ResponseEntity<User> responseEntity =
                restTemplate.getForEntity("/" + user.getEmail(), User.class);
        User rsltUser = responseEntity.getBody();
        assertEquals(user.getFirstName(), rsltUser.getFirstName());
    }

    @Test
    public void whenDeletingUserThenUserNotFound() {
        User user = new User("Ivan", "Ivanov", Date.valueOf("2000-01-01"), "ivanov@mail.ru", "12345");
        restTemplate.postForObject("/", user, String.class);
        restTemplate.delete("/"+user.getEmail());
        assertEquals(HttpStatus.NOT_FOUND, restTemplate.getForEntity("/" + user.getEmail(),String.class).getStatusCode());
    }
}