package ru.simbial.shoppingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.simbial.shoppingapp.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAdmin(){
        return "admin";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/users")
    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/{userId}")
    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "users";
    }
}