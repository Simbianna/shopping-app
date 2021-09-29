package ru.simbial.shoppingapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.simbial.shoppingapp.entity.User;

import java.util.List;



public interface UserService extends UserDetailsService {
    /*User findByUserName(String username);*/

   /* User create(User user);

    void update(User user);*/

    boolean saveUser(User user);

    User findUserById(Long userId);

    User updateUser(User user);

    List<User> getAllUsers();

    boolean delete(Long userId);
}
