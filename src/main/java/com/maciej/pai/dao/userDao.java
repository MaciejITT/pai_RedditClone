package com.maciej.pai.dao;
import com.maciej.pai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userDao extends JpaRepository<User,Integer> {
    public User findByLogin(String login);
    public boolean existsByLogin(String login);
}
