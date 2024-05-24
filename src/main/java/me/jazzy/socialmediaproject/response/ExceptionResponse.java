package me.jazzy.socialmediaproject.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}