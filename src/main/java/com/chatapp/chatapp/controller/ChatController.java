package com.chatapp.chatapp.controller;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.service.MessageService;
import com.chatapp.chatapp.dto.MessageRequest;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class ChatController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    // ✅ Constructor injection (IMPORTANT)
    public ChatController(MessageService messageService,
                          SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    // ✅ WebSocket endpoint
    @MessageMapping("/sendMessage")
    public void sendMessage(MessageRequest request) {

        Message savedMessage = messageService.saveMessage(request);

        // ✅ Send to channel-specific topic
        messagingTemplate.convertAndSend(
                "/topic/channel/" + request.getChannelId(),
                savedMessage
        );
    }
}