package ru.burmistrov.taskManager_React.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.burmistrov.taskManager_React.entity.User;
import ru.burmistrov.taskManager_React.entity.dto.UserDto;
import ru.burmistrov.taskManager_React.entity.enumerated.Role;
import ru.burmistrov.taskManager_React.repository.IUserRepository;

import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/signUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto signUp(@RequestBody @NotNull final UserDto userDto) {
        if (userRepository.findOneByLogin(Objects.requireNonNull(userDto.getLogin())) == null) {
            User user = new User();
            user.setLogin(userDto.getLogin());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setMiddleName("middle name");
            user.setEmail(userDto.getEmail());
            user.setRole(Role.COMMON_USER);
            userRepository.save(user);
            return userDto;
        }
        return null;

    }
}
