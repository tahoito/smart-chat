package com.example.smartchat.controller;

import com.example.smartchat.entity.Chat;
import com.example.smartchat.repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chats")
public class ChatController {
    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository){
        this.chatRepository = chatRepository; 
    }

    @GetMapping
    public List<Chat> getAllChats(){
        return chatRepository.findAll();
    }
    
    @PostMapping
    public Chat createChat(@RequestBody Chat chat){
        return chatRepository.save(chat);
    }
}
