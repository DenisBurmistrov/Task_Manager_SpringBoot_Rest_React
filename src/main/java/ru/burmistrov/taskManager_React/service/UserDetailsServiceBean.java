package ru.burmistrov.taskManager_React.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.burmistrov.taskManager_React.entity.CustomUser;
import ru.burmistrov.taskManager_React.entity.User;
import ru.burmistrov.taskManager_React.entity.enumerated.Role;
import ru.burmistrov.taskManager_React.repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("userDetailsService")
public class UserDetailsServiceBean implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = findByLogin(username);

        if (user == null) throw new UsernameNotFoundException("User not found");
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());
        CustomUser customUser = new CustomUser
                (username, Objects.requireNonNull(user.getPassword()),roles);
        customUser.setUser(user);
        return customUser;
    }

    private User findByLogin(String username) {
        if(username == null || username.isEmpty()) return null;
        return userRepository.findOneByLogin(username);
    }
}
