package com.team.backend.repository;

import com.team.backend.model.PendingPair;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendingPairRepository extends JpaRepository<PendingPair, Long> {
    Optional<PendingPair> findByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);

    @Transactional
    void deleteByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);
}