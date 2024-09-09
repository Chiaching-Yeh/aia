package com.systex.demo.controller.app;

import com.systex.demo.service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class OpenAiGptController {

    @Value("${line.bot.userId}")
    private String userId;

    @Autowired
    WebScrapingService webScrapingService;

    @Autowired
    OpenAiGptService openAiGptService;

    @Autowired
    KeyWordService keyWordService;

    @Autowired
    DataContentService dataContentService;

    @Autowired
    LineBotService lineBotService;

    @RequestMapping(value = "/api/v1/pushLineMessage", method = RequestMethod.GET)
    public String pushLineMessage(final HttpServletRequest request, final ModelMap model) throws IOException {

        try {
            // 先清空資料庫
            dataContentService.destroy();

            // 網頁抓取到的內容寫入資料庫
            webScrapingService.doWebScrapping();

            // 關鍵字
            String[] keyword = keyWordService.findAll();

            // 資料
            String[] data = dataContentService.findEverySourceTop5();

            // 串接 openai api
            StringBuilder response = openAiGptService.connectOPENAI("", data, keyword);
            String formattedResponse = response.toString().replace("\n", "<br>");

            // 推播 LINE
            lineBotService.pushMessageToUser(response.toString(), userId);

            model.addAttribute("status","OK!");
            model.addAttribute("aiResponse", formattedResponse);
            model.addAttribute("current", LocalDateTime.now());

        } catch (Exception e) {
            log.info("status",e.getMessage());
            model.addAttribute("status",e.getMessage());
        }

        return "gpt";

    }

    @RequestMapping(value = "/api/v1/pushLineMessageTest", method = RequestMethod.GET)
    public String pushLineMessageTest(final HttpServletRequest request, final ModelMap model) throws IOException {

        try {
            // 先清空資料庫
            dataContentService.destroy();

            // 串接 openai api
            StringBuilder response = openAiGptService.testOPENAI();

            String formattedResponse = response.toString().replace("\n", "<br>");

            // 推播 LINE
            lineBotService.pushMessageToUser(response.toString(), userId);

            model.addAttribute("status","OK!");
            model.addAttribute("aiResponse", formattedResponse);
            model.addAttribute("current", LocalDateTime.now());

        } catch (Exception e) {
            log.info("status",e.getMessage());
            model.addAttribute("status",e.getMessage());
        }

        return "gpt";

    }


    @RequestMapping(value = "/api/v1/pushLineMessageEasyTest", method = RequestMethod.GET)
    public String pushLineMessageEasyTest(final HttpServletRequest request, final ModelMap model) throws IOException {

        try {
            // 串接 openai api
            StringBuilder response = openAiGptService.easyTestOPENAI();

            // 推播 LINE
            lineBotService.pushMessageToUser(response.toString(), userId);
            String formattedResponse = response.toString().replace("\n", "<br>");

            model.addAttribute("status","OK!");
            model.addAttribute("aiResponse", formattedResponse);
            model.addAttribute("current", LocalDateTime.now());

        } catch (Exception e) {
            log.info("status",e.getMessage());
            model.addAttribute("status",e.getMessage());
        }

        return "gpt";

    }




}
