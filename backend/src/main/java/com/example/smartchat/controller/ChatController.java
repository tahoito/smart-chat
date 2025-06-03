package com.example.smartchat.controller;

import com.example.smartchat.entity.Chat;
import com.example.smartchat.repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

        //ユーザーのチャットを保存する
        chat savedChat = chatRepository.save(chat);

        //Botの返信を生成する
        Chat botReply =  new Chat();
        botReply.setName("Bot");
        botReply.setText("こんにちは！お話ししましょう！");
        botReply.setCreatedAt(LocalDateTime.now());

        chatRepository.save(botReply);
        return List.of(savedChat,botReply);
    }
}
