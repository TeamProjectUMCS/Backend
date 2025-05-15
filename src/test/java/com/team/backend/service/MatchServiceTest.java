package com.team.backend.service;

import com.team.backend.model.Enum.LikedStatus;
import com.team.backend.model.Enum.Preference;
import com.team.backend.model.Enum.Sex;
import com.team.backend.model.Match;
import com.team.backend.model.PendingPair;
import com.team.backend.model.User;
import com.team.backend.repository.MatchRepository;
import com.team.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PendingPairService pendingPairService;

    @InjectMocks
    private MatchService matchService;

    private User likingUser;
    private User likedUser;
    private PendingPair pendingPair;

    @BeforeEach
    void setup() {
        likingUser = User.of("liker", "likerLogin", "pass", Sex.MALE, Preference.WOMEN);
        likingUser.setId(1L);

        likedUser = User.of("liked", "likedLogin", "pass", Sex.FEMALE, Preference.MEN);
        likedUser.setId(2L);

        pendingPair = new PendingPair();
    }

    @Test
    void handleLike_shouldThrow_whenLikingUserNotFound() {
        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            matchService.handleLike("unknown", 2L);
        });
    }

    @Test
    void handleLike_shouldThrow_whenLikedUserNotFound() {
        when(userRepository.findByUsername("liker")).thenReturn(Optional.of(likingUser));
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            matchService.handleLike("liker", 99L);
        });
    }

    @Test
    void handleLike_shouldCreateMatchAndDeletePendingPair_whenBothLiked() {
        when(userRepository.findByUsername("liker")).thenReturn(Optional.of(likingUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(likedUser));
        when(pendingPairService.getOrCreatePendingPair(likingUser, likedUser)).thenReturn(pendingPair);

        when(pendingPairService.bothUsersLiked(pendingPair)).thenReturn(true);

        boolean result = matchService.handleLike("liker", 2L);

        assertTrue(result);
        verify(pendingPairService).updatePairStatus(pendingPair, likingUser, LikedStatus.LIKED);
        verify(matchRepository).save(any(Match.class));
        verify(pendingPairService).deletePendingPair(pendingPair);
    }

    @Test
    void handleLike_shouldNotCreateMatch_whenOnlyOneSideLiked() {
        when(userRepository.findByUsername("liker")).thenReturn(Optional.of(likingUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(likedUser));
        when(pendingPairService.getOrCreatePendingPair(likingUser, likedUser)).thenReturn(pendingPair);

        when(pendingPairService.bothUsersLiked(pendingPair)).thenReturn(false);

        boolean result = matchService.handleLike("liker", 2L);

        assertFalse(result);
        verify(pendingPairService).updatePairStatus(pendingPair, likingUser, LikedStatus.LIKED);
        verify(matchRepository, never()).save(any());
        verify(pendingPairService, never()).deletePendingPair(any());
    }
}

