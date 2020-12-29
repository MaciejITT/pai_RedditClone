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
    private String user;

    @NotNull
    private Integer votes = 0;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{10,150}", message="Tytuł musi zawierać od 10 do 150 znaków")
    private String post_name;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{30,600}", message="Tytuł musi zawierać od 30 do 600 znaków")
    private String post_content;

    public Post() {
    }
    public Post(String user) {
        this.user=user;
        this.votes = 0;
    }
    public Post(String user, String post_name, String post_content) {
        this.user=user;
        this.votes = 0;
        this.post_name = post_name;
        this.post_content = post_content;
    }
    public Post(String user,Integer votes, String post_name, String post_content) {
        this.user=user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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
