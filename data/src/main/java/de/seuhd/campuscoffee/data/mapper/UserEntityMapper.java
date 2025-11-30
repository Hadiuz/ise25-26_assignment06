package de.seuhd.campuscoffee.data.mapper;

import de.seuhd.campuscoffee.data.persistence.UserEntity;
import de.seuhd.campuscoffee.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

/**
 * MapStruct mapper for converting between domain models and JPA entities.
 * This mapper handles the translation between the {@link User} domain model and the
 * {@link UserEntity} persistence entity.
 * <p>
 * This is part of the data layer adapter in the hexagonal architecture, enabling the
 * domain layer to remain independent of persistence concerns.
 */
@Mapper(componentModel = "spring")
@ConditionalOnMissingBean // prevent IntelliJ warning about duplicate beans
public interface UserEntityMapper {
    @Mapping(source = "loginName", target = "login")
    @Mapping(source = "emailAddress", target = "email")
    @Mapping(source = "createdAt", target = "creationTimestamp")
    @Mapping(source = "updatedAt", target = "updateTimestamp")
    User fromEntity(UserEntity source);

    @Mapping(source = "login", target = "loginName")
    @Mapping(source = "email", target = "emailAddress")
    @Mapping(source = "creationTimestamp", target = "createdAt")
    @Mapping(source = "updateTimestamp", target = "updatedAt")
    UserEntity toEntity(User source);

    /**
     * Updates an existing JPA entity with data from the domain model.
     * This method is intended for update operations where the entity already exists.
     * JPA-managed fields (id, createdAt, updatedAt) are preserved and not overwritten.
     *
     * @param source the domain model containing the new data; must not be null
     * @param target the existing JPA entity to update; must not be null
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "login", target = "loginName")
    @Mapping(source = "email", target = "emailAddress")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    void updateEntity(User source, @MappingTarget UserEntity target);
}
