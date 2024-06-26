package me.jazzy.socialmediaproject.exception.badrequest;

import me.jazzy.socialmediaproject.exception.base.BaseException;
import org.springframework.http.HttpStatus;

public class UserBadRequestException extends BaseException {
    public UserBadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}