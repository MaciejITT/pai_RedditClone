package com.maciej.pai.controllers;
import com.maciej.pai.dao.userDao;
import com.maciej.pai.entity.User;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private userDao dao;

    @GetMapping("/login")
    public String loginPage() {
        //zwrócenie nazwy widoku logowania - login.html
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        //dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
        //zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@ModelAttribute(value = "user") @Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "register"; // powrót do formularza
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        //przekierowanie do adresu url: /login
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        //dodanie do modelu obiektu user - aktualnie zalogowanego użytkownika:
        m.addAttribute("user", dao.findByLogin(principal.getName()));
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }

    @GetMapping("/users")
    public String Users(Model m){
        m.addAttribute("userList", dao.findAll());
        return "users";
    }
    @GetMapping("/editUser")
    public String editPage(Model m, Principal principal) {
        m.addAttribute("userData", dao.findByLogin(principal.getName()));
        return "editUser";
    }
    @PostMapping("/editUser")
    public String editUserPagePOST(@ModelAttribute(value = "userData") @Valid User userData,BindingResult binding,Principal principal) {
        if (binding.hasErrors()) {
            return "editUser"; // powrót do formularza
        }
        User updateUser = dao.findByLogin(principal.getName());
        updateUser.setName(userData.getName());
        updateUser.setSurname(userData.getSurname());
        updateUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        dao.save(updateUser);
        return "redirect:/profile";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(Principal principal) {
        User user = dao.findByLogin(principal.getName());
        dao.delete(user);
        return "redirect:/login";
    }

}
