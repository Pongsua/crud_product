package com.product.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.User;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
