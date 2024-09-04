package com.systex.demo.service;

import com.linecorp.bot.messaging.client.MessagingApiClient;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.ReplyMessageRequest;
import com.linecorp.bot.messaging.model.TextMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LineBotService {

    @Autowired
    protected MessagingApiClient messagingApiClient;

    /**
     * 回覆訊息給用戶
     *
     * @param replyToken  來自 LINE 平台的回覆 token
     * @param messageText 要發送的訊息內容
     */
    public void replyToUser(String replyToken, String messageText) {
        // 檢查參數是否為空
        if (replyToken == null || replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken cannot be null or empty");
        }
        if (messageText == null || messageText.isEmpty()) {
            throw new IllegalArgumentException("messageText cannot be null or empty");
        }

        // 創建訊息和回覆對象
        TextMessage textMessage = new TextMessage("你說了:"+messageText);
        List<Message> messages = List.of(textMessage);
        ReplyMessageRequest replyMessageRequest = new
                ReplyMessageRequest(replyToken, messages, false);

        // 使用 MessagingApiClient 發送回覆訊息
        messagingApiClient.replyMessage(replyMessageRequest)
                .whenComplete((response, throwable) -> {
                    if (throwable != null) {
                        // 處理錯誤情況，並記錄具體的錯誤信息
                        System.err.println("Error while sending message: " + throwable.getMessage());
                        throwable.printStackTrace();
                    } else {
                        // 成功發送後的響應
                        System.out.println("Successfully sent message: " + messageText);
                    }
                });
    }

}



