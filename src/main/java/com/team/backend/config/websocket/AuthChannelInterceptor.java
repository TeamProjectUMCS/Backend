package com.team.backend.config.websocket;

import com.team.backend.config.security.JwtAuthFacade;
import com.team.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@SuppressWarnings("ALL")
@Component
@RequiredArgsConstructor
@Log4j2
public class AuthChannelInterceptor implements ChannelInterceptor {
    private final JwtAuthFacade jwtAuthFacade;
    private final UserService userService;
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor != null) {
            log.info("Received WebSocket message: type={}, destination={}, payload={}",
                    accessor.getCommand(),
                    accessor.getDestination(),
                    new String((byte[]) message.getPayload()));

            // Log connection events
            if (StompCommand.CONNECT.equals(accessor.getCommand())) {

                List<String> authorization = accessor.getNativeHeader("Authorization");
                log.debug("WebSocket Connection - Authorization Header: {}", authorization);

                if (authorization != null && !authorization.isEmpty()) {
                    String token = authorization.get(0).replace("Bearer ", "");
                    try {
                        String login = jwtAuthFacade.getUsername(token);
                        if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            UserDetails userDetails = userService.loadUserByLogin(login);
                            Authentication auth = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(auth);
                            var test = SecurityContextHolder.getContext().getAuthentication().getName();
                            accessor.setUser(auth);
                            log.info("WebSocket authenticated for user: {}", login);
                        }
                    } catch (Exception e) {
                        log.error("WebSocket authentication error", e);
                    }
                }
            } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
                log.info("Client disconnected: sessionId={}", accessor.getSessionId());
            } else if (StompCommand.SUBSCRIBE.equals(accessor.getCommand())) {
                log.info("Client subscribed to: {}, sessionId={}", accessor.getDestination(), accessor.getSessionId());
            } else if (StompCommand.SEND.equals(accessor.getCommand())) {
                log.info("Client sent message to: {}, sessionId={}", accessor.getDestination(), accessor.getSessionId());
            }
        }
        return message;
    }
}
