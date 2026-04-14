package com.chatapp.chatapp.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public MessageResponse(Long id, String sender, String content, LocalDateTime timestamp) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}