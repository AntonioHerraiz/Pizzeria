package com.example.api_spring_boot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_spring_boot_crud.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
