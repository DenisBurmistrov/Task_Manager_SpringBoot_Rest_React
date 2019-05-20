package ru.burmistrov.taskManager_React.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.taskManager_React.entity.enumerated.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_project")
@NoArgsConstructor
public class Project implements Serializable {

    @NotNull
    @Id
    private String id = UUID.randomUUID().toString();

    @Nullable
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "dateBegin")
    private Date dateBegin = new Date();

    @Nullable
    @Column(name = "dateEnd")
    private Date dateEnd;

    @Nullable
    @Column(name = "user_id")
    private String userId;

    @Nullable
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Nullable
    @OneToMany(cascade = {CascadeType.REMOVE}, mappedBy = "projectId", orphanRemoval = true)
    private List<Task> tasks;

}
