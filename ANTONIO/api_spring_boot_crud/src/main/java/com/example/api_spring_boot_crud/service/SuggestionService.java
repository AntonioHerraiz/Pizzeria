package com.example.api_spring_boot_crud.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_spring_boot_crud.model.Suggestion;
import com.example.api_spring_boot_crud.repository.SuggestionRepository;

import java.util.List;

@Service
@Transactional
public class SuggestionService {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<Suggestion> listAllSuggestion() {
        return suggestionRepository.findAll();
    }

    public void saveSuggestion(Suggestion suggestion) {
        suggestionRepository.save(suggestion);
    }

    public Suggestion getSuggestion(Integer id) {
        return suggestionRepository.findById(id).get();
    }

    public void deleteSuggestion(Integer id) {
        suggestionRepository.deleteById(id);
    }

    public void updateSuggestionById(Suggestion suggestion) {
        // Suggestion s = suggestionRepository.findById(suggestion.getId()).get();
        // (s.setName(suggestion.getName());
        // s.setUser(suggestion.getUser());
        // s.setDate(suggestion.getDate());
        // s.setIngredients(suggestion.getIngredients());

        // suggestionRepository.save(s);
    }
}
