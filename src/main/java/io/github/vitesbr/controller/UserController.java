package io.github.vitesbr.controller;

import io.github.vitesbr.entity.User;
import io.github.vitesbr.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired UserRepository users;

    @GetMapping
    public List<User> list() {
        return this.users.findAll();
    }

    @GetMapping("{id}")
    public User getById( @PathVariable Integer id ){
        return this.users
        		.findById(id)
        		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }
}
