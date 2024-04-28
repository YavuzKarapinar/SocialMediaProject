package me.jazzy.socialmediaproject.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Verification {
    private boolean status;
    private LocalDateTime startedAt;
    private LocalDateTime endsAt;
    private String planType;
}