package ru.burmistrov.taskManager_React.entity.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String login;

    @Nullable
    private String password;

    @Nullable
    private String email;

}
