package com.example.api_spring_boot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_spring_boot_crud.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
}
