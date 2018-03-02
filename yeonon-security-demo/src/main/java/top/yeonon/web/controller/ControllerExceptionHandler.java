package top.yeonon.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import top.yeonon.exception.UserServiceException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserServiceException(UserServiceException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }
}
