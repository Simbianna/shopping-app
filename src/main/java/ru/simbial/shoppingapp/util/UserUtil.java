package ru.simbial.shoppingapp.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.simbial.shoppingapp.entity.Role;
import ru.simbial.shoppingapp.entity.User;

import java.util.Collections;

public class UserUtil {

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setRoles(Collections.singleton(Role.USER));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
