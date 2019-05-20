package ru.burmistrov.taskManager_React.service;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.burmistrov.taskManager_React.api.service.IProjectService;
import ru.burmistrov.taskManager_React.entity.Project;
import ru.burmistrov.taskManager_React.entity.dto.ProjectDto;
import ru.burmistrov.taskManager_React.entity.enumerated.Status;
import ru.burmistrov.taskManager_React.repository.IProjectRepository;
import ru.burmistrov.taskManager_React.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;

    @Autowired
    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Nullable
    public ProjectDto findOneById(@NotNull final String id, @NotNull final String userId) throws ParseException {
        Project project = projectRepository.findOne(id, userId);
        if(project != null) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());
            projectDto.setDescription(project.getDescription());
            projectDto.setDateBegin(DateUtil.parseDate(project.getDateBegin()));
            projectDto.setDateEnd(DateUtil.parseDate(project.getDateEnd()));
            return projectDto;
        }
        return null;
    }

    @NotNull
    public ProjectDto persist(@NotNull final ProjectDto projectDto, @NotNull final String userId) throws ParseException {
        @NotNull final Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setDateEnd(DateUtil.parseString(projectDto.getDateEnd()));
        project.setUserId(userId);
        project.setStatus(Status.SCHEDULED);
        projectRepository.save(project);
        return projectDto;
    }

    @NotNull
    public ProjectDto merge(@NotNull final ProjectDto projectDto, @NotNull final String userId) throws ParseException {
        @NotNull final Project project = projectRepository.findOne(Objects.requireNonNull(projectDto.getId()), userId);
        if (project != null) {
            project.setName(projectDto.getName());
            project.setDescription(projectDto.getDescription());
            project.setDateEnd(DateUtil.parseString(projectDto.getDateEnd()));
            projectRepository.save(project);
            return projectDto;
        }
        return null;
    }

    @Nullable
    public List<ProjectDto> findAll(@NotNull final String userId) throws ParseException {
        List<Project> projects = projectRepository.findAll(userId);
        List<ProjectDto> result = new ArrayList<>();
        for(Project project : projects) {
            ProjectDto projectDto = new ProjectDto();
            projectDto.setId(project.getId());
            projectDto.setName(project.getName());
            projectDto.setDescription(project.getDescription());
            projectDto.setDateBegin(DateUtil.parseDate(project.getDateBegin()));
            projectDto.setDateEnd(DateUtil.parseDate(project.getDateEnd()));
            result.add(projectDto);
        }
        return result;
    }

    @Nullable
    public void removeById(@NotNull final String id, @NotNull final String userId) {
        Project project = projectRepository.findOne(id, userId);
        if(project != null)
        projectRepository.delete(project);
    }
}
