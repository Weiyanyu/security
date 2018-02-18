package top.yeonon.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserServiceException extends RuntimeException {

    private Long id;

    private String message;

    public UserServiceException(Long id, String message) {
        super(message);
        this.message = message;
        this.id = id;
    }



}
