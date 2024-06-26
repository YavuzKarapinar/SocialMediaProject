package me.jazzy.socialmediaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String contact;
    private String location;
    private LocalDateTime birthDate;
    private String image;
    private String backgroundImage;
    private String bio;
    private boolean reqUser;
    private boolean loginWithGoogle;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Like> likes;

    @Embedded
    Verification verification;

    @JsonIgnore
    @ManyToMany
    List<User> followers;

    @JsonIgnore
    @ManyToMany
    List<User> followings;

    public User(
            String name,
            String email,
            String password,
            LocalDateTime birthDate,
            Verification verification) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.verification = verification;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("USER");
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}