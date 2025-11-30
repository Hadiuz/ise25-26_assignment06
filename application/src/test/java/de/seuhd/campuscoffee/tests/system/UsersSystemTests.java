package de.seuhd.campuscoffee.tests.system;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import org.junit.jupiter.api.Test;

import java.util.List;

import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

public class UsersSystemTests extends AbstractSysTest {

    @Test
    void createUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
    }

    @Test
    void retrieveUserById() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        User retrievedUser = userDtoMapper.toDomain(userRequests.retrieveById(createdUser.id()));

        assertEqualsIgnoringTimestamps(retrievedUser, createdUser);
    }

    @Test
    void deleteUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toDomain(userRequests.create(List.of(userDtoMapper.fromDomain(userToCreate))).getFirst());

        List<Integer> statusCodes = userRequests.deleteAndReturnStatusCodes(List.of(createdUser.id()));
        assertThat(statusCodes).containsExactly(NO_CONTENT.value());

        // Verify that the user is no longer retrievable
        List<Integer> statusCodesAfterDelete = userRequests.deleteAndReturnStatusCodes(List.of(createdUser.id()));
        assertThat(statusCodesAfterDelete).containsExactly(NOT_FOUND.value());
    }
}