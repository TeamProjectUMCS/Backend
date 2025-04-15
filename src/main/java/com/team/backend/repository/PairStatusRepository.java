package com.team.backend.repository;

import com.team.backend.model.PairStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PairStatusRepository extends JpaRepository<PairStatus, Long> {
    Optional<PairStatus> findById(Long id);

    @Transactional
    PairStatus save(PairStatus pairStatus);
}
