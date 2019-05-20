package ru.burmistrov.taskManager_React.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.taskManager_React.entity.Task;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {

    Task save(@NotNull final Task task);

    @Modifying
    void delete(@NotNull final Task task);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.id = :taskId AND task.userId = :userId")
    Task findOne(@NotNull @Param(value = "taskId") final String id, @NotNull @Param(value = "userId") final String userId);

    @Nullable
    @Query(value = "SELECT task FROM Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    List<Task> findAllByProjectId(@NotNull @Param(value = "userId") final String userId, @NotNull @Param(value = "projectId") final String projectId);
}
