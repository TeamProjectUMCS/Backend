package com.team.backend.repository;

import com.team.backend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Message save(Message message);

    void deleteById(Long id);

    List<Message> findByMatchId(Long matchId);
}
