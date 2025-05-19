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

    @Transactional
    PendingPair save(PendingPair pendingPair);

    @Transactional
    void deleteByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);

    @Query("""
            SELECT p FROM PendingPair p
            WHERE p.firstUserStatus.user = :user
            """)
    List<PendingPair> findByFirstUser(@Param("user") User user);

    @Transactional
    void deleteById(Long id);

    Optional<PendingPair> findByFirstUserStatusIdAndSecondUserStatusId(Long firstUserStatusId, Long secondUserStatusId);

    @Query("""
            SELECT p FROM PendingPair p
            WHERE
                (p.firstUserStatus.user = :userA AND p.secondUserStatus.user = :userB)
                OR
                (p.firstUserStatus.user = :userB AND p.secondUserStatus.user = :userA)
            """)
    Optional<PendingPair> findByUsers(@Param("userA") User userA, @Param("userB") User userB);
}