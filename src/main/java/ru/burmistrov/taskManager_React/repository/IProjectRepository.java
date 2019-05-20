package ru.burmistrov.taskManager_React.repository;


import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.taskManager_React.entity.Project;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    Project save(@NotNull final Project project);

    void delete(@NotNull final Project project);

    @Query(value = "SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAll(@NotNull @Param(value = "userId") final String userId);

    @Query(value = "SELECT project FROM Project project WHERE project.id = :projectId AND project.userId = :userId")
    Project findOne(@NotNull @Param(value = "projectId") final String id, @NotNull @Param(value = "userId") final String userId);
}
