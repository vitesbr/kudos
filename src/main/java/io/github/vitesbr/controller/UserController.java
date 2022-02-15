package io.github.vitesbr.controller;

import io.github.vitesbr.entity.User;
import io.github.vitesbr.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private UserRepository users;

	public UserController(UserRepository users) {
		this.users = users;
	}

    @GetMapping
    public List<User> list() {
        return this.users.findAll();
    }

    @GetMapping("{id}")
    public Optional<User> getById( @PathVariable Integer id ){
        return this.users.findById(id);
    }
}
