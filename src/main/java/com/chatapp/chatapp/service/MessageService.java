package com.chatapp.chatapp.service;

import com.chatapp.chatapp.model.Message;
import com.chatapp.chatapp.model.Channel;
import com.chatapp.chatapp.model.User;
import com.chatapp.chatapp.repository.MessageRepository;
import com.chatapp.chatapp.repository.ChannelRepository;
import com.chatapp.chatapp.repository.UserRepository;
import com.chatapp.chatapp.dto.MessageRequest;
import com.chatapp.chatapp.dto.MessageResponse;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository,
                          ChannelRepository channelRepository,
                          UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.channelRepository = channelRepository;
        this.userRepository = userRepository;
    }

    public Message saveMessage(MessageRequest request) {

        Channel channel = channelRepository.findById(request.getChannelId())
                .orElseThrow(() -> new RuntimeException("Channel not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Message message = new Message();
        message.setContent(request.getContent());
        message.setChannel(channel);
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        return messageRepository.save(message);
    }

    public List<MessageResponse> getAllMessages() {
        return messageRepository.findAllByOrderByTimestampAsc()
                .stream()
                .map(msg -> new MessageResponse(
                        msg.getId(),
                        msg.getUser().getUsername(),
                        msg.getContent(),
                        msg.getTimestamp()
                ))
                .collect(Collectors.toList());
    }

    public List<MessageResponse> getMessagesByChannel(Long channelId) {
        return messageRepository.findByChannel_IdOrderByTimestampAsc(channelId)
                .stream()
                .map(msg -> new MessageResponse(
                        msg.getId(),
                        msg.getUser().getUsername(),
                        msg.getContent(),
                        msg.getTimestamp()
                ))
                .collect(Collectors.toList());
    }
}