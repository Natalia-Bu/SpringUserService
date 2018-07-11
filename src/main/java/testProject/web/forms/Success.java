package testProject.web.forms;

import org.springframework.http.HttpStatus;

public class Success<T> extends Result<T> {

    public Success(T value) {

        super(value, HttpStatus.OK);
    }
}