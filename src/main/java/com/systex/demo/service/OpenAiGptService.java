package com.systex.demo.service;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class OpenAiGptService {

    @Autowired
    OpenAiService openAiService;

    public final static String systemSetting = "You are an experienced financial planner, and your job is to summarize daily investment trends and recommendations based on financial keywords and key points from global financial news and Taiwan stock market news. This helps beginner investors who don't have the time to read a large volume of global and Taiwan stock market news by providing the best trends and recommendations.";

    public String defaultString =
            "The following is a pseudocode prompt, please follow the meaning and process step by step for execution.\n" +
            "The response content should be written in Traditional Chinese.\n" +
            "Disable the code interpreter, but enable browsing for searches.\n" +
            "The following analysis should be assumed to be for the Taiwan market \n.";


    @Transactional
    public ChatMessage connectOPENAI(String userSettingStr, String[] data, String[] keywords) {

        ChatMessage responseMessage = null;

        try {

            List<ChatMessage> messages = new ArrayList<>();
            ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(), systemSetting);
            messages.add(systemMessage);

            StringBuilder userSetting = new StringBuilder();
            if (!userSettingStr.isEmpty()) {
                userSetting = new StringBuilder(userSettingStr);
            } else {
                userSetting = new StringBuilder(defaultString);
                userSetting.append("Based on the source following information[").append(Arrays.toString(data)).append("] and financial-related keywords[").append(Arrays.toString(keywords)).append("]summarize how global stock markets are impacting today's operation of the Taiwan stock market, and provide the best trends and recommendations\n.");
            }

            ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), userSetting.toString());
            messages.add(userMessage);

            ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                    .builder()
                    .model("gpt-4o")
                    .messages(messages)
                    .n(1)
                    .stream(true)
                    .maxTokens(500)
                    .logitBias(new HashMap<>())
                    .build();

            responseMessage = openAiService.createChatCompletion(chatCompletionRequest).getChoices().get(0).getMessage();

            System.out.println("responseMessage>" + responseMessage);

            return responseMessage;

        } catch (OpenAiHttpException e) {
            log.error("Error occurred while creating completion: ", e);
        }

        return responseMessage;

    }

    public HashMap<String,Object> paramsSetting (String data, String keyWord){
        HashMap pramaMap = new HashMap();


        return pramaMap;
    }



}
