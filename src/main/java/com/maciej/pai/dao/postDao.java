package com.maciej.pai.dao;
import com.maciej.pai.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postDao extends CrudRepository<Post, Integer> {
    public Post findPostByPostid(Integer id);
}