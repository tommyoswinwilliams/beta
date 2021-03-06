package com.makersacademy.acebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GenerationType;

import com.makersacademy.acebook.service.*;

import java.util.List;
import java.net.URL;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import org.ocpsoft.prettytime.PrettyTime;

import lombok.Data;

@Data
@Entity
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String content;
    private String username;
    private String imagePath;
    private String imageFileName;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date created_at;

    // @ManyToMany(mappedBy="likedPosts") // links to bridge table
    // Set<User> likes = new HashSet<User>(); // Creates a 'Set' of 'Users' called likes.
    // Each user associated with a post represents 1 like
    
   @OneToMany(mappedBy="post_id")
   Set<Like> likes = new HashSet<Like>(); 

    @OneToMany(mappedBy = "post")
    List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Post() {
    }

    public Post(String content, String username) {
        this.content = content;
        this.username = username;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikesCount() {
        return String.valueOf(this.likes.size());
    }
    // returns number of users in set. I.e number of likes.

    public List<Comment> getComments() {
        return this.comments;
    }

    public User getUser() {
        return this.user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public long getID() {
        return this.id;
    }

    public Date getCreatedAt() {
        return this.created_at;
    }

    public String getFormattedTimestamp() {
        PrettyTime p = new PrettyTime();
        return (p.format(getCreatedAt()));
    }

    public URL getProfilePictureUrl() {
        FileStore fileStore = new FileStore();
        return fileStore.getUrl(user.getImagePath(), user.getImageFileName());
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

    public URL getPostPictureUrl() {
        FileStore fileStore = new FileStore();
        return fileStore.getUrl(this.imagePath, this.imageFileName);
    }

}
