package com.chatapp.chatapp.controller;

import com.chatapp.chatapp.model.Channel;
import com.chatapp.chatapp.repository.ChannelRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/channels")
public class ChannelController {

    private final ChannelRepository channelRepository;

    public ChannelController(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    // Create channel
    @PostMapping
    public Channel createChannel(@RequestBody Channel channel) {
        return channelRepository.save(channel);
    }

    // Get all channels
    @GetMapping
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    // Get channel by ID
    @GetMapping("/{id}")
    public Channel getChannelById(@PathVariable Long id) {
        return channelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Channel not found"));
    }
}