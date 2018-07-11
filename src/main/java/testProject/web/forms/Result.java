package testProject.web.forms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Result<T> extends ResponseEntity<T> {
    Result(T body, HttpStatus status) {
        super(body, status);
    }
}
