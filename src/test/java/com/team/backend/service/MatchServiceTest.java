package com.team.backend.service;

import com.team.backend.model.Enum.Preference;
import com.team.backend.model.Enum.Sex;
import com.team.backend.model.Hobby;
import com.team.backend.model.User;
import com.team.backend.repository.MatchRepository;
import com.team.backend.repository.PendingPairRepository;
import com.team.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock private MatchRepository matchRepository;
    @Mock private UserRepository userRepository;
    @Mock private PendingPairRepository pendingPairRepository;

    @InjectMocks private MatchService matchService;

    private User user;
    private User candidate1, candidate2;

    @BeforeEach
    void setup() {
        user = User.of("user", "login", "pass", Sex.MALE, Preference.WOMEN);
        user.setId(1L);
        user.setAge(25);
        user.setAge_min(20);
        user.setAge_max(30);
        user.setLocalization("Lublin");

        Hobby hobby = new Hobby();
        hobby.setId(1L);
        user.setHobbies(List.of(hobby));

        candidate1 = User.of("c1", "c1login", "pass", Sex.FEMALE, Preference.MEN);
        candidate1.setId(2L);
        candidate1.setAge(26);
        candidate1.setLocalization("Lublin");
        candidate1.setHobbies(List.of(hobby));

        //inne hobby
        candidate2 = User.of("c2", "c2login", "pass", Sex.FEMALE, Preference.MEN);
        candidate2.setId(3L);
        candidate2.setAge(27);
        candidate2.setLocalization("Lublin");
        candidate2.setHobbies(List.of());
    }

    @Test
    void shouldReturnOnlyCompatiblePotentialMatches() {
        List<User> filteredUsers = List.of(candidate1, candidate2);
        when(userRepository.findFilteredByAgeAndLocation(user.getId(), user.getAge_min(), user.getAge_max(), user.getLocalization()))
                .thenReturn(filteredUsers);

        when(matchRepository.findAllMatchesForUser(user)).thenReturn(List.of());
        when(pendingPairRepository.findByFirstUser(user)).thenReturn(List.of());


        List<User> potentialMatches = matchService.getPotentialMatches(user);


        assertEquals(1, potentialMatches.size());
        assertTrue(potentialMatches.contains(candidate1));
        assertFalse(potentialMatches.contains(candidate2)); // brak wspolnych hobby
    }
}
