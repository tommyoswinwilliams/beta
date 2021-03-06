package com.makersacademy.acebook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.*;
import java.net.URL;

import com.makersacademy.acebook.service.*;

import lombok.Data;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean enabled;
    private String imagePath;
    private String imageFileName;

    // @ManyToMany
    // @JoinTable(name = "liked_posts", // links to bridge table
    //         joinColumns = @JoinColumn(name = "user_id"), // FK
    //         inverseJoinColumns = @JoinColumn(name = "post_id")) // FK
    // Set<Post> likedPosts;

    public User() {
        this.enabled = TRUE;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = TRUE;
    }

    public User(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Long getId() {
        return this.id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String encodedPassword = encoder.encode(password);
        this.password = encodedPassword;
    }

    public void setImagePath(String path) {
        this.imagePath = path;
    }

    public void setImageFileName(String fileName) {
        this.imageFileName = fileName;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getImageFileName() {
        return this.imageFileName;
    }

    public URL getProfilePictureUrl() {
        FileStore fileStore = new FileStore();
        return fileStore.getUrl(this.imagePath, this.imageFileName);
    }
}
