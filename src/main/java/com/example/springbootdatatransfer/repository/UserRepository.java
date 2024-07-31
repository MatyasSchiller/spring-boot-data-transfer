package com.example.springbootdatatransfer.repository;

import com.example.springbootdatatransfer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
