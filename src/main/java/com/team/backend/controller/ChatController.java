package com.team.backend.controller;

import com.team.backend.model.dto.MessageRequestDto;
import com.team.backend.model.dto.MessageResponseDto;
import com.team.backend.service.ChatService;
import com.team.backend.service.MatchService;
import com.team.backend.service.MessageService;
import com.team.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
@Log4j2
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final UserService userService;
    private final MatchService matchService;
    private final ChatService chatService;

    @MessageMapping("/chat/{matchId}")
    public void processMessage(@DestinationVariable String matchId, MessageRequestDto messageRequestDto, Authentication authentication) {
        log.info("Received message for match {}: {}", matchId, messageRequestDto);
        if (authentication != null) {
            log.info("Message sent by authenticated user: {}", authentication.getName());
        } else {
            log.warn("Message received without authentication");
            // Make sure the message contains a valid senderId
            if (messageRequestDto.writtenBy() == null) {
                log.error("Message rejected: No sender ID provided");
                return;
            }
        }

        MessageResponseDto messageResponseDto = chatService.processIncomingMessage(Long.parseLong(matchId), messageRequestDto);

        // Send the message to all subscribers
        messagingTemplate.convertAndSend("/topic/messages/" + matchId, messageResponseDto);

        log.info("Received and saved message to match {}: {}", matchId, messageResponseDto);
    }


    @GetMapping("/chat/{matchId}")
    public ResponseEntity<List<MessageResponseDto>> getMessages(@PathVariable Long matchId) {
        List<MessageResponseDto> messages = chatService.getChatMessagesForMatch(matchId);
        return ResponseEntity.ok(messages);
    }

}
