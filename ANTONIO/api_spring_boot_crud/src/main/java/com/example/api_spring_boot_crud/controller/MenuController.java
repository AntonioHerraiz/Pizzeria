package com.example.api_spring_boot_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api_spring_boot_crud.model.Menu;
import com.example.api_spring_boot_crud.service.MenuService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    MenuService menuService;

    @GetMapping("")
    public List<Menu> list() {
        return menuService.listAllMenu();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> get(@PathVariable Integer id) {
        try {
            Menu menu = menuService.getMenu(id);
            try {
                System.out.println(menu.toString());
            } catch (Exception e) {
                System.err.println("Error print menu controller");
            }
            return new ResponseEntity<Menu>(menu, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Menu>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Menu menu) {
        menuService.saveMenu(menu);
    }

    @PutMapping("/{id}/")
    public ResponseEntity<?> update(@RequestBody Menu menu, @PathVariable Integer id) {
        try {
            menu.setId(id);
            menuService.saveMenu(menu);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public void delete(@PathVariable Integer id) {

        menuService.deleteMenu(id);
    }
}
