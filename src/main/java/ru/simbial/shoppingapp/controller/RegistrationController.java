package ru.simbial.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.simbial.shoppingapp.entity.Role;
import ru.simbial.shoppingapp.entity.User;
import ru.simbial.shoppingapp.service.UserService;

import javax.validation.Valid;


@Controller
public class RegistrationController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
           if (bindingResult.getModel().containsKey("name")){
               model.addAttribute("nameError", "invalid name");
           }
            if (bindingResult.getModel().containsKey("password")){
                model.addAttribute("passwordError", "invalid password");
            }
            return "registration";

        }

        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
}
