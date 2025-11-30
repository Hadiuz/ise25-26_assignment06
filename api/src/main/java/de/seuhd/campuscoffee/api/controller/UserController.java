package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.UserDto;
import de.seuhd.campuscoffee.api.mapper.UserDtoMapper;
import de.seuhd.campuscoffee.domain.ports.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Operations related to user management.")
@Controller
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    @ResponseBody
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(userDtoMapper::fromDomain)
                .toList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public UserDto getUserById(@PathVariable Long id) {
        return userDtoMapper.fromDomain(userService.getUserById(id));
    }

    @GetMapping("/filter")
    @ResponseBody
    public UserDto getUserByLoginName(@RequestParam("loginName") String loginName) {
        return userDtoMapper.fromDomain(userService.getUserByLoginName(loginName));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        var user = userDtoMapper.toDomain(userDto);
        var createdUser = userService.createUser(user);
        return new ResponseEntity<>(userDtoMapper.fromDomain(createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public UserDto updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        var user = userDtoMapper.toDomain(userDto);
        // Ensure the ID from the path is used, not from the body
        var userWithId = user.toBuilder().id(id).build();
        return userDtoMapper.fromDomain(userService.updateUser(userWithId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
