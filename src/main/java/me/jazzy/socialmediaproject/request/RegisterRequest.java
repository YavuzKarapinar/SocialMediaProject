package me.jazzy.socialmediaproject.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String birthDate;
}