package com.maciej.pai.service;
import com.maciej.pai.entity.User;
import com.maciej.pai.entity.Post;
import java.util.List;

public interface PostService {
    Post findById(Integer id);
    void save(Post post);
    List<Post> findByUserId(Integer id);
    void saveForUser(Post password, User user);
}
