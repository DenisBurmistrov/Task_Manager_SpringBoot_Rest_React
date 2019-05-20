package ru.burmistrov.taskManager_React.api.service;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import ru.burmistrov.taskManager_React.entity.dto.TaskDto;

import java.text.ParseException;
import java.util.List;

public interface ITaskService {

    @Nullable
    TaskDto findOneById(@NotNull final String id, @NotNull final String userId) throws ParseException;

    @NotNull
    TaskDto persist(@NotNull final TaskDto taskDto, @NotNull final String userId) throws ParseException;

    @NotNull
    TaskDto merge(@NotNull final TaskDto taskDto, @NotNull final String userId) throws ParseException;

    @Nullable
    List<TaskDto> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws ParseException;

    void removeById(@NotNull final String id, @NotNull final String userId);
}
