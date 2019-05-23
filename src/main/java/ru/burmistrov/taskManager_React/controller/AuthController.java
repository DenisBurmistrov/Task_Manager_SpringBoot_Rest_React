package ru.burmistrov.taskManager_React.controller;


import com.sun.istack.NotNull;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.burmistrov.taskManager_React.entity.dto.LoginDto;

import java.text.ParseException;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void auth(@RequestBody @NotNull final LoginDto loginDto) throws ParseException {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
