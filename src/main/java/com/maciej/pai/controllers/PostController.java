package com.maciej.pai.controllers;
import com.maciej.pai.entity.Post;
import com.maciej.pai.dao.userDao;
import com.maciej.pai.dao.postDao;
import com.maciej.pai.entity.User;

import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PostController {

    @Autowired
    private userDao dao;

    @Autowired
    private postDao postdao;


    @GetMapping("/*")
    public String resolveUnknownURL(){
        return "unknownURL";
    }

    @GetMapping("/addPost")
    public String addPostPage(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        m.addAttribute("post", new Post(user.getLogin()));
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPostPagePOST(@ModelAttribute(value = "post") @Valid Post post, BindingResult binding, Principal principal) {
        if (binding.hasErrors()) {
            return "addPost"; // powrót do formularza
        }
        post.setUser(dao.findByLogin(principal.getName()));
        postdao.save(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts/delete/*")
    public String noIdToDeleteFound(){
        return "redirect:/posts";
    }
    @GetMapping("/posts")
    public String postsPage(Model m, Principal principal) {
        m.addAttribute("postsList", postdao.findAll());
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        return "posts";
    }
    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable int id){
        postdao.deleteById(id);
        return "redirect:/posts";
    }
    @GetMapping("/posts/more/{id}")
    public String moreAboutPost(@PathVariable Integer id, Model m){
        m.addAttribute("post",postdao.findPostByPostid(id));
        return "viewPost";
    }
    @GetMapping("/posts/edit/{id}")
    public String editPost(@PathVariable Integer id, Model m, Principal principal){
        Post post_to_edit = postdao.findPostByPostid(id);
        User user = dao.findByLogin(principal.getName());
        m.addAttribute("post", post_to_edit);
        if(post_to_edit.getUser().getUserid() != user.getUserid()){
            return "redirect:/posts";
        }else {
            return "editPost";
        }
    }
    @PostMapping("/editPost")
    public String editUserPagePOST(@ModelAttribute(value = "post") @Valid Post post,BindingResult binding,Principal principal) {
        if (binding.hasErrors()) {
            return "editPost"; // powrót do formularza
        }
        Post updatePost = postdao.findPostByPostid(post.getPostid());
        updatePost.setUser(dao.findByLogin(principal.getName()));
        updatePost.setPost_name(post.getPost_name());
        updatePost.setPost_content(post.getPost_content());

        postdao.save(updatePost);
        return "redirect:/posts";
    }
}
