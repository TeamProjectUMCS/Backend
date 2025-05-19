package com.team.backend.repository;

import com.team.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);

    void deleteById(Long id);

    List<Message> findByMatchId(Long matchId);

    List<Message> findByMatchIdOrderByTimestampDesc(Long matchId);

    Optional<Message> findFirstByMatchIdOrderByTimestampDesc(Long matchId);
}
