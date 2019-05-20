package ru.burmistrov.taskManager_React.api.service;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import ru.burmistrov.taskManager_React.entity.dto.ProjectDto;

import java.text.ParseException;
import java.util.List;

public interface IProjectService {

    @Nullable
    ProjectDto findOneById(@NotNull final String id, @NotNull final String userId) throws ParseException;

    @NotNull
    ProjectDto persist(@NotNull final ProjectDto projectDto, @NotNull final String userId) throws ParseException;

    @Nullable
    ProjectDto merge(@NotNull final ProjectDto projectDto, @NotNull final String userId) throws ParseException;

    @NotNull
    List<ProjectDto> findAll(@NotNull final String userId) throws ParseException;

    void removeById(@NotNull final String id, @NotNull final String userId);
}
