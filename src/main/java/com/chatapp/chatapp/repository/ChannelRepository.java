package com.chatapp.chatapp.repository;

import com.chatapp.chatapp.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}