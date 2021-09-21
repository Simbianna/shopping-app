package ru.simbial.shoppingapp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.simbial.shoppingapp.entity.User;
import ru.simbial.shoppingapp.repository.UserRepository;


import javax.transaction.Transactional;
import java.util.*;

import static ru.simbial.shoppingapp.util.UserUtil.prepareToSave;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User findByUserName(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid userName or password");
        }
        return user; /*new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));*/
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return userRepository.save(prepareToSave(user, passwordEncoder));
    }

    @Transactional
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        userRepository.save(prepareToSave(user, passwordEncoder));
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean delete(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
/* private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().
                map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }*/