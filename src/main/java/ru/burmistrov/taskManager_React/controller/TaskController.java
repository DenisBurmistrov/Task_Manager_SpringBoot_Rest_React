package ru.burmistrov.taskManager_React.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.burmistrov.taskManager_React.api.service.ITaskService;
import ru.burmistrov.taskManager_React.entity.CustomUser;
import ru.burmistrov.taskManager_React.entity.dto.TaskDto;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/task")
public class TaskController {

    private final ITaskService taskService;

    @Autowired
    public TaskController(@NotNull final ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public TaskDto findProjectById(@PathVariable @NotNull final String id, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return taskService.findOneById(id, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public TaskDto persist(@RequestBody @NotNull final TaskDto taskDto, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return taskService.persist(taskDto, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public TaskDto merge(@RequestBody @NotNull final TaskDto taskDto, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return taskService.merge(taskDto, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @GetMapping(value = "/list/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public List<TaskDto> findAllTasksInProject(@PathVariable @NotNull final String projectId, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return taskService.findAllInProject(Objects.requireNonNull(customUser.getUser()).getId(), projectId);
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public List<TaskDto> findAllTasks(@NotNull final Authentication authentication) throws ParseException {
          @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return taskService.findAll(Objects.requireNonNull(customUser.getUser()).getId());
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public void removeProjectGet(@PathVariable final String id, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        taskService.removeById(id, Objects.requireNonNull(customUser.getUser()).getId());
    }
}
