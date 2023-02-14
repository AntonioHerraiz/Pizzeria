package com.example.api_spring_boot_crud.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_spring_boot_crud.model.Menu;
import com.example.api_spring_boot_crud.repository.MenuRepository;

import java.util.List;

@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> listAllMenu() {
        return menuRepository.findAll();
    }

    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu getMenu(Integer id) {
        return menuRepository.findById(id).get();
    }

    public void deleteMenu(Integer id) {
        menuRepository.deleteById(id);
    }

    public void updateMenuById(Menu menu) {
        Menu m = menuRepository.findById(menu.getId()).get();
        m.setImage(menu.getImage());
        m.setName(menu.getName());
        m.setIngredients(menu.getIngredients());
        m.setPrice(menu.getPrice());

        menuRepository.save(m);
    }
}
