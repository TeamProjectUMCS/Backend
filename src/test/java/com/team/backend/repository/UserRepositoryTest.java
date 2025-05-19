package com.team.backend.repository;

import com.team.backend.model.Enum.*;
import com.team.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUsersByAgeAndLocationAndExcludeCurrentUser() {
        User currentUser = new User();
        currentUser.setUsername("marek");
        currentUser.setLogin("maro");
        currentUser.setPassword("pass");
        currentUser.setAge(25);
        currentUser.setLocalization("Lublin");
        currentUser.setSex(Sex.MALE);
        currentUser.setPreference(Preference.BOTH);
        userRepository.save(currentUser);

        User matchingUser = new User();
        matchingUser.setUsername("krysia");
        matchingUser.setLogin("krysia");
        matchingUser.setPassword("pass");
        matchingUser.setAge(26);
        matchingUser.setLocalization("Lublin");
        matchingUser.setSex(Sex.FEMALE);
        matchingUser.setPreference(Preference.BOTH);
        userRepository.save(matchingUser);

        User nonMatchingUser = new User();
        nonMatchingUser.setUsername("bob");
        nonMatchingUser.setLogin("bob");
        nonMatchingUser.setPassword("pass");
        nonMatchingUser.setAge(35);
        nonMatchingUser.setLocalization("Krakow");
        nonMatchingUser.setSex(Sex.MALE);
        nonMatchingUser.setPreference(Preference.BOTH);
        userRepository.save(nonMatchingUser);

        List<User> results = userRepository.findFilteredByAgeAndLocation(
                currentUser.getId(), 20, 30, "Lublin");

        assertThat(results)
                .containsExactly(matchingUser)
                .doesNotContain(currentUser, nonMatchingUser);
    }
}


