package de.seuhd.campuscoffee.domain.model;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder(toBuilder = true)
public record User(
        Long id,
        LocalDateTime creationTimestamp,
        LocalDateTime updateTimestamp,
        String login,
        String email,
        String firstName,
        String lastName
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public User {
        Objects.requireNonNull(login);
        Objects.requireNonNull(email);
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
    }
}
