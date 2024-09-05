package com.systex.demo.controller;


import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.*;
import com.systex.demo.service.LineBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@LineMessageHandler
@RestController
public class LineMessageHandlerController {

    @Autowired
    LineBotService lineBotService;

    private TextMessage buildDefaultTextMessage() {
        String replayText = "您好~歡迎您寄信到「服務信箱」\n" +
                "（）\n" +
                "針對使用有任何疑問可連絡以下資訊：\n" +
                "客服專線：886-2-0000-0000\n" +
                "傳真：886-2-2000000\n" +
                "E-mail:charlieyeh1114@gmail.com";

        return new TextMessage.Builder(replayText)
                .build();
    }

    @EventMapping
    public Message handleTextMessageEvent(MessageEvent event) throws Exception {

        System.out.println("handleTextMessageEvent st");

        // 使用 message() 方法來取得訊息內容
        if (event.message() instanceof TextMessageContent textMessageContent) {
            String receivedMessage = textMessageContent.text();  // 使用 text() 方法來獲取文本
            log.info("Received message: {}", receivedMessage);

            // 回覆相同的訊息內容
//            return new TextMessage("您說了: " + receivedMessage);
            handleTextContent(event, textMessageContent, event.replyToken());

        }

        // 如果不是文字訊息，回覆這段訊息
        return new TextMessage("目前只支援文字訊息。");
    }


    private void handleTextContent(Event event, TextMessageContent content, String replyToken) throws Exception {

        final Source source = event.source();
        final String text = content.text();
        final String senderId = source.userId();

        log.info("source: {}", source);
        log.info("text: {}", text);
        log.info("senderId: {}", senderId);

        lineBotService.replyToUser(replyToken,content.text());

    }

    @EventMapping
    public void handleDefaultMessageEvent(MessageEvent event) {
        log.info("Received event: {}", event);
    }




}
