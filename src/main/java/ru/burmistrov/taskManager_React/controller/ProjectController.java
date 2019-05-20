package ru.burmistrov.taskManager_React.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.burmistrov.taskManager_React.api.service.IProjectService;
import ru.burmistrov.taskManager_React.entity.CustomUser;
import ru.burmistrov.taskManager_React.entity.dto.ProjectDto;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    private final IProjectService projectService;

    @Autowired
    public ProjectController(@NotNull final IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public ProjectDto findProjectById(@PathVariable @NotNull final String id, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return projectService.findOneById(id, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public ProjectDto persist(@RequestBody @NotNull final ProjectDto projectDto, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return projectService.persist(projectDto, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public ProjectDto merge(@RequestBody @NotNull final ProjectDto projectDto, @NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return projectService.merge(projectDto, Objects.requireNonNull(customUser.getUser()).getId());
    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public List<ProjectDto> findAllProjects(@NotNull final Authentication authentication) throws ParseException {
        @NotNull final CustomUser customUser = (CustomUser) authentication.getPrincipal();
        return projectService.findAll(Objects.requireNonNull(customUser.getUser()).getId());
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('COMMON_USER') or hasAuthority('ADMINISTRATOR')")
    public void removeProjectGet(@PathVariable final String id, Authentication authentication) {
        CustomUser customUser = (CustomUser) authentication.getPrincipal();
        projectService.removeById(id, Objects.requireNonNull(customUser.getUser()).getId());
    }
}
