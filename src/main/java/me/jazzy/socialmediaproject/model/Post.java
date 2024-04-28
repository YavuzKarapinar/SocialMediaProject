package me.jazzy.socialmediaproject.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sharingDate;
    private String content;
    private String image;
    private String video;
    private boolean isReply;
    private boolean isPost;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Like> likes;

    @OneToMany
    List<Post> repliedPosts;

    @ManyToMany
    List<User> rePostedUsers;

    @ManyToOne
    Post replyFor;
}