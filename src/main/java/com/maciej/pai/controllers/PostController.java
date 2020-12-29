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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PostController {

    @Autowired
    private userDao dao;

    @Autowired
    private postDao postdao;

    @GetMapping("/addPost")
    public String addPostPage(Model m, Principal principal) {
        User user = dao.findByLogin(principal.getName());
        m.addAttribute("post", new Post(user.getLogin()));
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPostPagePOST(@ModelAttribute(value = "post") @Valid Post post, BindingResult binding) {
        if (binding.hasErrors()) {
            return "addPost"; // powrót do formularza
        }

        postdao.save(post);
        return "redirect:/profile";
    }
}
