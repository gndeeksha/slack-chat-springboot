package com.chatapp.chatapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.chatapp.chatapp.model.Message;
import java.util.List;
import com.chatapp.chatapp.service.MessageService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.chatapp.chatapp.dto.MessageRequest;
import com.chatapp.chatapp.dto.MessageResponse;
import org.springframework.web.bind.annotation.PathVariable;
import com.chatapp.chatapp.model.Channel;


@RestController
@RequestMapping("/api")
public class MessageController {

//    @GetMapping("/hello")//When user goes to /hello, run this method
//    public String sayHello() {
//        return "Hello, your Spring Boot app is working!";
//    }
//    @GetMapping("/bye")
//    public String sayBye() {
//        return "Goodbye from Spring Boot!";
//    }
//@GetMapping("/greet")
//public String greetUser(String name) {
//    return "Hello " + name + "!";
////}

@PostMapping("/message")
public String sendMessage(@RequestBody MessageRequest request) {
    messageService.saveMessage(request);
    return "Saved: " + request.getContent();
}

    @GetMapping("/messages")
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }
    @GetMapping("/messages/{channelId}")
    public List<MessageResponse> getMessagesByChannel(@PathVariable Long channelId) {
        return messageService.getMessagesByChannel(channelId);
    }

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
}