package com.example.smartchat.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGptService{
    @Value("${openai.api.key}")
    private String apiKey;

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String askChatGPt(String userMessage){
        //requestBodyを生成する
        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(Map.of("role", "user", "content", userMessage)));

        //ヘッダーを設定
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // Authorization: Bearer {APIキー}

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        //postリクエストを送信する
        RestTemplate restTemplate = new RestTemplate();
        ResponsesEntity<Map> responses = restTemplate.exchange(API_URL,HttpMethod.POST, requestEntity, Map.class);

        // 応答からChatGPTの返事を取得
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");

        return (String) message.get("content");
    }
}