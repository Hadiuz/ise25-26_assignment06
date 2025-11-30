package de.seuhd.campuscoffee.domain.impl;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.ports.UserDataService;
import de.seuhd.campuscoffee.domain.ports.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDataService userDataService;

    @Override
    public void clear() {
        userDataService.clear();
    }

    @Override
    @NonNull
    public List<User> getAllUsers() {
        return userDataService.getAll();
    }

    @Override
    @NonNull
    public User getUserById(@NonNull Long id) {
        return userDataService.getById(id);
    }

    @Override
    @NonNull
    public User getUserByLoginName(@NonNull String loginName) {
        return userDataService.getByLoginName(loginName);
    }

    @Override
    @NonNull
    public User createUser(@NonNull User user) {
        return userDataService.upsert(user);
    }

    @Override
    @NonNull
    public User updateUser(@NonNull User user) {
        return userDataService.upsert(user);
    }

    @Override
    public void deleteUser(@NonNull Long id) {
        userDataService.delete(id);
    }
}
