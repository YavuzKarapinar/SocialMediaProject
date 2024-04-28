package me.jazzy.socialmediaproject.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "likes")
@Data
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
}