package com.example.api_spring_boot_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api_spring_boot_crud.model.Suggestion;
import com.example.api_spring_boot_crud.service.SuggestionService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/suggestions")
public class SuggestionController {
    @Autowired
    SuggestionService suggestionService;

    @GetMapping("")
    public List<Suggestion> list() {
        return suggestionService.listAllSuggestion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suggestion> get(@PathVariable Integer id) {
        try {
            Suggestion suggestion = suggestionService.getSuggestion(id);
            try {
                System.out.println(suggestion.toString());
            } catch (Exception e) {
                System.err.println("Error print suggestion controller");
            }
            return new ResponseEntity<Suggestion>(suggestion, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Suggestion>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public void add(@RequestBody Suggestion suggestion) {
        suggestionService.saveSuggestion(suggestion);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<?> update(@RequestBody Suggestion suggestion, @PathVariable Integer id) {
        try {
            suggestion.setId(id);
            suggestionService.saveSuggestion(suggestion);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public void delete(@PathVariable Integer id) {

        suggestionService.deleteSuggestion(id);
    }
}
