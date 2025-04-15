package com.team.backend.service;

import com.team.backend.model.PairStatus;
import com.team.backend.repository.PairStatusRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PairStatusService {

    private final PairStatusRepository pairStatusRepository;

    public PairStatus createPairStatus(PairStatus pairStatus) {
        return pairStatusRepository.save(pairStatus);
    }

    public Optional<PairStatus> findPairStatusById(Long id) {
        return pairStatusRepository.findById(id);
    }

    @Transactional
    public void deletePairStatus(Long id) {
        pairStatusRepository.deleteById(id);
    }

    public PairStatus updatePairStatus(PairStatus pairStatus) {
        return pairStatusRepository.save(pairStatus);
    }
}
