package com.maciej.pai.entity;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userid;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Podaj poprawnie imię 2-30 znaków")
    private String name;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Podaj poprawnie nazwisko 2-30 znaków")
    private String surname;

    @NotNull
    @Pattern(regexp = "[a-zA-Z]{2,30}", message="Podaj poprawny login 2-30 znaków")
    private String login;

    @NotNull
    @NotBlank( message="Pole jest wymagane")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "user")
    private List<Post> userPosts;

    public User() {
    }
    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

