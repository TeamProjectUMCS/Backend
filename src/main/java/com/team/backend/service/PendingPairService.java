package com.team.backend.service;

import com.team.backend.model.PendingPair;
import com.team.backend.repository.PendingPairRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PendingPairService {

    private final PendingPairRepository pendingPairRepository;

    public Optional<PendingPair> findPendingPair(Long firstUserStatusId, Long secondUserStatusId) {
        return pendingPairRepository.findByFirstUserStatusIdAndSecondUserStatusId(firstUserStatusId, secondUserStatusId);
    }

    public PendingPair createPendingPair(PendingPair pendingPair) {
        return pendingPairRepository.save(pendingPair);
    }

    @Transactional
    public void deletePendingPair(Long firstUserStatusId, Long secondUserStatusId) {
        pendingPairRepository.deleteByFirstUserStatusIdAndSecondUserStatusId(firstUserStatusId, secondUserStatusId);
    }

    public PendingPair updatePendingPair(PendingPair pendingPair) {
        return pendingPairRepository.save(pendingPair);
    }
}
