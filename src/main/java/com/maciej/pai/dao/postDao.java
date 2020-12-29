package com.maciej.pai.dao;
import com.maciej.pai.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface postDao extends CrudRepository<Post, Integer> {
}