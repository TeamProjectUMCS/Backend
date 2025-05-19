package com.team.backend.repository;

import com.team.backend.model.PendingPair;
import com.team.backend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PendingPairRepository extends JpaRepository<PendingPair, Long> {
    Optional<PendingPair> findByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);

    @Transactional
    void deleteByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);

    @Query("""
            SELECT p FROM PendingPair p
            WHERE p.firstUserStatus.user = :user
            """)
    List<PendingPair> findByFirstUser(@Param("user") User user);
}