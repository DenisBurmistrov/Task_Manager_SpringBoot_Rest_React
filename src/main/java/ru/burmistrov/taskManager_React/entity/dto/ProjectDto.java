package ru.burmistrov.taskManager_React.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDto {

    @Nullable
    private String id;

    @Nullable
    private String name;

    @Nullable
    private String description;

    @Nullable
    private String dateBegin;

    @Nullable
    private String dateEnd;
}
