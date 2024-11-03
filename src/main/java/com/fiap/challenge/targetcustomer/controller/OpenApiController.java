package com.fiap.challenge.targetcustomer.controller;

import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/help")
public class OpenApiController {
    @Autowired
    private OpenAiChatClient openAiChatClient;

    @GetMapping
    public ResponseEntity<String> newChat(@RequestParam(value = "message", defaultValue = "Olá, mundo!") String message){
        return ResponseEntity.ok(openAiChatClient.call(message));
    }
}
