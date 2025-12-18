package my.learn.mireaffjpractice12.exception.handler;

import my.learn.mireaffjpractice12.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {

        Map<String,Object> map = new HashMap<>();
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));

        Map<String,Object> body = new HashMap<>();
        body.put("message","Переданы невалидные данные");
        body.put("errors",errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException e) {
        Map<String,Object> body = new HashMap<>();
        body.put("message",e.getMessage());

        return new ResponseEntity<>(body, e.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthException(AuthenticationException e) {
        Map<String,Object> body = new HashMap<>();
        body.put("message",e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }




}
