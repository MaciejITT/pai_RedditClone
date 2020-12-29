package com.maciej.pai.dao;
import com.maciej.pai.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface userDao extends CrudRepository<User, Integer> {
    public User findByLogin(String login);
}
