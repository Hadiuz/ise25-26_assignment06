package de.seuhd.campuscoffee.api.mapper;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    @Mapping(source = "loginName", target = "login")
    @Mapping(source = "emailAddress", target = "email")
    User toDomain(UserDto userDto);

    @Mapping(source = "login", target = "loginName")
    @Mapping(source = "email", target = "emailAddress")
    @Mapping(target = "createdAt", source = "creationTimestamp")
    @Mapping(target = "updatedAt", source = "updateTimestamp")
    UserDto fromDomain(User user);
}
