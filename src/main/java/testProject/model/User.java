package testProject.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class describing entity User
 */
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;
    private String secondName;
    private Date birthDate;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public User() {
    }

    /**
     * Class constructor to create and fill object
     *
     * @param firstName  user name(not null)
     * @param secondName user surname
     * @param birthDate  user date of birth
     * @param email      user email (not null, unique)
     * @param password   user password (not null)
     */
    public User(String firstName, String secondName, Date birthDate, String email, String password) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
