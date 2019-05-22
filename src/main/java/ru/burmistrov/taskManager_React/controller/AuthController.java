package ru.burmistrov.taskManager_React.controller;


import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void auth(@RequestBody @NotNull final String username,
                        @RequestBody @NotNull final String password) throws ParseException {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
