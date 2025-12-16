package my.learn.mireaffjpractice12.exception;

import org.springframework.http.HttpStatus;

public class RawAppException extends AppException{

    public RawAppException(String message, HttpStatus status) {
       super(message, status);
    }
}
