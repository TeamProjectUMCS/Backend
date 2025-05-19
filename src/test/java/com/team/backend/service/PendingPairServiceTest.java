package com.team.backend.service;

import com.team.backend.model.Enum.LikedStatus;
import com.team.backend.model.Enum.Preference;
import com.team.backend.model.Enum.Sex;
import com.team.backend.model.PairStatus;
import com.team.backend.model.PendingPair;
import com.team.backend.model.User;
import com.team.backend.repository.PairStatusRepository;
import com.team.backend.repository.PendingPairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PendingPairServiceTest {

    @Mock
    private PendingPairRepository pendingPairRepository;

    @Mock
    private PairStatusRepository pairStatusRepository;

    @InjectMocks
    private PendingPairService pendingPairService;

    private User user1;
    private User user2;

    @BeforeEach
    void setup() {
        user1 = User.of("username1", "login1", "password1", Sex.MALE, Preference.WOMEN);
        user1.setId(1L);

        user2 = User.of("username2", "login2", "password2", Sex.FEMALE, Preference.MEN);
        user2.setId(2L);
    }

    @Test
    void shouldCreatePendingPairWithTwoPendingStatuses() {

        ArgumentCaptor<PendingPair> captor = ArgumentCaptor.forClass(PendingPair.class);

        when(pendingPairRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        PendingPair result = pendingPairService.createPendingPair(user1, user2);

        assertEquals(LikedStatus.PENDING, result.getFirstUserStatus().getLikedStatus());
        assertEquals(LikedStatus.PENDING, result.getSecondUserStatus().getLikedStatus());

        verify(pendingPairRepository).save(captor.capture());
        assertNotNull(captor.getValue());
    }

    @Test
    void shouldReturnExistingPendingPairIfExists() {
        PairStatus ps1 = new PairStatus(user1, LikedStatus.PENDING);
        PairStatus ps2 = new PairStatus(user2, LikedStatus.PENDING);
        PendingPair existingPair = new PendingPair(ps1, ps2);

        when(pendingPairRepository.findByUsers(same(user1), same(user2)))
                .thenReturn(Optional.of(existingPair));

        PendingPair result = pendingPairService.getOrCreatePendingPair(user1, user2);

        assertEquals(existingPair, result);

        verify(pendingPairRepository, never()).save(any());
    }

    @Test
    void shouldCreateAndSavePendingPairIfNotExists() {
        when(pendingPairRepository.findByUsers(eq(user1), eq(user2)))
                .thenReturn(Optional.empty());

        when(pendingPairRepository.save(any(PendingPair.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PendingPair result = pendingPairService.getOrCreatePendingPair(user1, user2);

        assertNotNull(result);
        assertEquals(LikedStatus.PENDING, result.getFirstUserStatus().getLikedStatus());
        assertEquals(LikedStatus.PENDING, result.getSecondUserStatus().getLikedStatus());

        verify(pendingPairRepository).save(any(PendingPair.class));
    }


    @Test
    void shouldUpdateFirstUserStatus() {

        PairStatus status = new PairStatus(user1, LikedStatus.PENDING);
        PendingPair pair = new PendingPair(status, new PairStatus());

        pendingPairService.updatePairStatus(pair, user1, LikedStatus.LIKED);

        assertEquals(LikedStatus.LIKED, pair.getFirstUserStatus().getLikedStatus());
        verify(pairStatusRepository).save(pair.getFirstUserStatus());
    }

    @Test
    void shouldReturnTrueIfBothUsersLiked() {
        PairStatus s1 = new PairStatus(new User(), LikedStatus.LIKED);
        PairStatus s2 = new PairStatus(new User(), LikedStatus.LIKED);
        PendingPair pair = new PendingPair(s1, s2);

        boolean result = pendingPairService.bothUsersLiked(pair);

        assertTrue(result);
    }

}