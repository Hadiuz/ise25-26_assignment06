package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.model.User;
import org.jspecify.annotations.NonNull;

import java.util.List;

public interface UserService {
    void clear();

    @NonNull
    List<User> getAllUsers();

    @NonNull
    User getUserById(@NonNull Long id);

    @NonNull
    User getUserByLoginName(@NonNull String loginName);

    @NonNull
    User createUser(@NonNull User user);

    @NonNull
    User updateUser(@NonNull User user);

    void deleteUser(@NonNull Long id);
}
