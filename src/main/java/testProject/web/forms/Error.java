package testProject.web.forms;

import org.springframework.http.HttpStatus;

public class Error extends Result<String> {
    public Error(String error, HttpStatus status) {
        super(error, status);
    }
}
