package com.example.smartchat.controller;

import com.example.smartchat.entity.Chat;
import com.example.smartchat.repository.ChatRepository;
import org.springframework.web.bind.annotation.*;

import com.example.smartchat.service.ChatGptService;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/chats")
public class ChatController {

    private final ChatRepository chatRepository;
    private final ChatGptService chatGptService; 


    public ChatController(ChatRepository chatRepository, ChatGptService chatGptService){
        this.chatRepository = chatRepository; 
        this.chatGptService = chatGptService; 
    }
    

    @GetMapping
    public List<Chat> getAllChats(){
        return chatRepository.findAll();
    }
    
    @PostMapping
    public Chat createChat(@RequestBody Chat chat){
        // ChatGPTにメッセージを送信して返事をもらう
        String reply = chatGptService.askChatGpt(chat.getText());
        // Chatエンティティに返信をセットするならここで
        System.out.println("GPTの返答: " + reply); // ← 動作確認

        // 必要なら保存もできる（今回は送信だけ）
        return chatRepository.save(chat);
    }

}
