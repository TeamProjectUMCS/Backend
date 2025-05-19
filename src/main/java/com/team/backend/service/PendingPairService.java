package com.team.backend.service;

import com.team.backend.model.Enum.LikedStatus;
import com.team.backend.model.PairStatus;
import com.team.backend.model.PendingPair;
import com.team.backend.model.User;
import com.team.backend.repository.PairStatusRepository;
import com.team.backend.repository.PendingPairRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PendingPairService {

    private final PendingPairRepository pendingPairRepository;
    private final PairStatusRepository pairStatusRepository;

    public Optional<PendingPair> findPendingPair(Long firstUserStatusId, Long secondUserStatusId) {
        return pendingPairRepository.findByFirstUserStatusIdAndSecondUserStatusId(firstUserStatusId, secondUserStatusId);
    }

    public PendingPair savePendingPair(PendingPair pendingPair) {
        return pendingPairRepository.save(pendingPair);
    }

    @Transactional
    public void deletePendingPair(Long firstUserStatusId, Long secondUserStatusId) {
        pendingPairRepository.deleteByFirstUserStatusIdAndSecondUserStatusId(firstUserStatusId, secondUserStatusId);
    }

    @Transactional
    public void deletePendingPair(PendingPair pendingPair) {
        pendingPairRepository.delete(pendingPair);
    }

    public PendingPair updatePendingPair(PendingPair pendingPair) {
        return pendingPairRepository.save(pendingPair);
    }

    public PendingPair getOrCreatePendingPair(User firstUser, User secondUser) {
        return pendingPairRepository.findByUsers(firstUser, secondUser)
                .orElseGet(() -> createPendingPair(firstUser, secondUser));
    }


    public PendingPair createPendingPair(User firstUser, User secondUser) {
        PairStatus firstUserStatus = new PairStatus(firstUser, LikedStatus.PENDING);

        PairStatus secondUserStatus = new PairStatus(secondUser, LikedStatus.PENDING);

        PendingPair pendingPair = new PendingPair(firstUserStatus, secondUserStatus);
        return pendingPairRepository.save(pendingPair);
    }

    public void updatePairStatus(PendingPair pair, User user, LikedStatus status) {
        PairStatus statusToUpdate = user.equals(pair.getFirstUserStatus().getUser())
                ? pair.getFirstUserStatus()
                : pair.getSecondUserStatus();

        statusToUpdate.setLikedStatus(status);
        pairStatusRepository.save(statusToUpdate);
    }

    public boolean bothUsersLiked(PendingPair pair) {
        return pair.getFirstUserStatus().getLikedStatus() == LikedStatus.LIKED &&
                pair.getSecondUserStatus().getLikedStatus() == LikedStatus.LIKED;
    }
}
