package ru.burmistrov.taskManager_React.service;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.burmistrov.taskManager_React.api.service.ITaskService;
import ru.burmistrov.taskManager_React.entity.Task;
import ru.burmistrov.taskManager_React.entity.dto.TaskDto;
import ru.burmistrov.taskManager_React.entity.enumerated.Status;
import ru.burmistrov.taskManager_React.repository.ITaskRepository;
import ru.burmistrov.taskManager_React.util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    @Autowired
    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Nullable
    public TaskDto findOneById(@NotNull final String id, @NotNull final String userId) throws ParseException {
        Task task = taskRepository.findOne(id, userId);
        if(task != null) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setName(task.getName());
            taskDto.setDescription(task.getDescription());
            taskDto.setProjectId(task.getProjectId());
            taskDto.setDateBegin(DateUtil.parseDate(task.getDateBegin()));
            taskDto.setDateEnd(DateUtil.parseDate(task.getDateEnd()));
            return taskDto;
        }
        return null;
    }

    @NotNull
    public TaskDto persist(@NotNull final TaskDto taskDto, @NotNull final String userId) throws ParseException {
        @NotNull final Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setProjectId(taskDto.getProjectId());
        task.setDateEnd(DateUtil.parseString(taskDto.getDateEnd()));
        task.setUserId(userId);
        task.setStatus(Status.SCHEDULED);
        taskRepository.save(task);
        taskDto.setId(task.getId());
        return taskDto;
    }

    @NotNull
    public TaskDto merge(@NotNull final TaskDto taskDto, @NotNull final String userId) throws ParseException {
        @NotNull final Task task = taskRepository.findOne(Objects.requireNonNull(taskDto.getId()), userId);
        if (task != null) {
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setDateEnd(DateUtil.parseString(taskDto.getDateEnd()));
            task.setProjectId(taskDto.getProjectId());
            taskRepository.save(task);
            return taskDto;
        }
        return null;
    }

    @Nullable
    public List<TaskDto> findAllInProject(@NotNull final String userId, @NotNull final String projectId) throws ParseException {
        List<Task> tasks = taskRepository.findAllByProjectId(userId, projectId);
        List<TaskDto> result = new ArrayList<>();
        if (tasks != null) {
            for (Task task : tasks) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(task.getId());
                taskDto.setName(task.getName());
                taskDto.setDescription(task.getDescription());
                taskDto.setProjectId(task.getProjectId());
                taskDto.setDateBegin(DateUtil.parseDate(task.getDateBegin()));
                taskDto.setDateEnd(DateUtil.parseDate(task.getDateEnd()));
                result.add(taskDto);
            }
        }
        return result;
    }

    @Override
    public List<TaskDto> findAll(String userId) throws ParseException {
        List<Task> tasks = taskRepository.findAll(userId);
        List<TaskDto> result = new ArrayList<>();
        if (tasks != null) {
            for (Task task : tasks) {
                TaskDto taskDto = new TaskDto();
                taskDto.setId(task.getId());
                taskDto.setName(task.getName());
                taskDto.setDescription(task.getDescription());
                taskDto.setProjectId(task.getProjectId());
                taskDto.setDateBegin(DateUtil.parseDate(task.getDateBegin()));
                taskDto.setDateEnd(DateUtil.parseDate(task.getDateEnd()));
                result.add(taskDto);
            }
        }
        return result;
    }

    @Override
    public void removeById(String id, String userId) {
        Task task = taskRepository.findOne(id, userId);
        if(task != null)
            taskRepository.delete(task);
    }
}
