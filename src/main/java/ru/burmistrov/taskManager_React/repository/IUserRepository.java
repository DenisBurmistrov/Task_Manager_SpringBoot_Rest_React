package ru.burmistrov.taskManager_React.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.burmistrov.taskManager_React.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User save(@NotNull final User user);

    void delete(@NotNull final User user);

    @Query(value = "SELECT user FROM User user WHERE user.id = :id")
    User findOne(@NotNull @Param(value = "id") final String id);

    @Query(value = "SELECT user FROM User user WHERE user.login = :login")
    User findOneByLogin(@NotNull @Param(value = "login") final String login);
}
