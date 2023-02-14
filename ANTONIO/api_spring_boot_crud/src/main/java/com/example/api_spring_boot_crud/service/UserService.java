package com.example.api_spring_boot_crud.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_spring_boot_crud.model.User;
import com.example.api_spring_boot_crud.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
        //return userRepository.findById(user.getId()).get();
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public void updateUserById(User user) {
        User u = userRepository.findById(user.getId()).get();
        u.setName(user.getName());
        u.setEmail(user.getEmail());

        userRepository.save(u);
    }
}
