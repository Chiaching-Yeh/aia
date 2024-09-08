package com.systex.demo.controller.app;

import com.systex.demo.service.LineBotService;
import com.systex.demo.service.WebScrapingService;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OpenAiGptController {

    @Autowired
    OpenAiService openAiService;

    @Autowired
    WebScrapingService webScrapingService;

    @Autowired
    LineBotService lineBotService;


    public String createResponse() {


        try {
            // 將 webCrawler並擷取片段資料存進資料庫

            // 將資料庫內片段撈出

            // 將關鍵字撈出


        } catch (Exception e) {
            log.error("Error occurred while creating completion: ", e);
        }

        return "/index";


    }



}
