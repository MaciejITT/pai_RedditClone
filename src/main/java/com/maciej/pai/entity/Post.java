package com.maciej.pai.entity;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postid;

    @NotNull
    private Integer votes = 0;

    @NotNull
    @Pattern(regexp = "[A-Z][A-z0-9_ ]{6,}", message="Tytuł musi zaczynać się z wielkiej litery i zawierać co najmniej 6 znaków")
    private String post_name;

    @NotNull
    @Column(length = 500)
    @Pattern(regexp = "[A-Z][A-z0-9_,'():;.?!@#$%&* ]{30,500}$", message="Tytuł musi zawierać od 30 do 500 znaków")
    private String post_content;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_posts")
    private User user;

    public Post() {
    }
    public Post(String user) {
        this.votes = 0;
    }
    public Post(String user, String post_name, String post_content) {
        this.votes = 0;
        this.post_name = post_name;
        this.post_content = post_content;
    }
    public Post(String user,Integer votes, String post_name, String post_content) {
        this.votes = votes;
        this.post_name = post_name;
        this.post_content = post_content;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }
}
