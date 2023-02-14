package com.example.api_spring_boot_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_spring_boot_crud.model.Suggestion;

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {

}