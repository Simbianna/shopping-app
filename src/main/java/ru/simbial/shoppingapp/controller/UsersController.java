package ru.simbial.shoppingapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.simbial.shoppingapp.entity.Role;
import ru.simbial.shoppingapp.entity.User;
import ru.simbial.shoppingapp.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "users";
    }

    @PostMapping()
    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                             @RequestParam(required = true, defaultValue = "" ) String action,
                             Model model) {
        if (action.equals("delete")){
            userService.delete(userId);
        }
        return "redirect:/admin/users";
    }


    @GetMapping("/edit/{id}")
    public String showEditUser(Model model, @PathVariable(value = "id") Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", Role.getRoles());
        return "user-edit";
        //  return "product-page";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute(value = "user") User user, @PathVariable(value = "id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin/users";
        //  return "product-page";
    }

    @PostMapping("/{id}")
    public String removeProduct(@PathVariable(value = "id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.findUserById(userId));
        return "users";
    }
}
