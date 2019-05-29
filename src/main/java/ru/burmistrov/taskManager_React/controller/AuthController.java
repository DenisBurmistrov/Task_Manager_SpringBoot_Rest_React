package ru.burmistrov.taskManager_React.controller;


import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.burmistrov.taskManager_React.entity.CustomUser;
import ru.burmistrov.taskManager_React.entity.dto.LoginDto;
import ru.burmistrov.taskManager_React.service.UserDetailsServiceBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final
    AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void auth(@RequestBody @NotNull final LoginDto loginDto, HttpServletRequest request) throws ParseException {

        final Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
    }
}
