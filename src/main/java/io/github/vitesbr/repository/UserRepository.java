package io.github.vitesbr.repository;

import io.github.vitesbr.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
