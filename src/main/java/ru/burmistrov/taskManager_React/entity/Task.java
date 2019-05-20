package ru.burmistrov.taskManager_React.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.burmistrov.taskManager_React.entity.enumerated.Status;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "app_task")
@NoArgsConstructor
public class Task implements Serializable {

    @NotNull
    @Id
    private String id = UUID.randomUUID().toString();

    @Nullable
    @Column(name = "project_id")
    private String projectId;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable=false, updatable=false)
    private Project project;
}
