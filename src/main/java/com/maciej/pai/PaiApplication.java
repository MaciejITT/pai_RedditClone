package com.maciej.pai;

import com.maciej.pai.dao.userDao;
import com.maciej.pai.entity.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;

@SpringBootApplication
public class PaiApplication {
	@Autowired
	private userDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(PaiApplication.class, args);
	}
	@PostConstruct
	public void init() {
		/*dao.save(new User("admin", "admin",
				"admin",passwordEncoder.encode("passwd")));*/
	}
}
