package com.systex.demo.controller.app;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Slf4j
@Controller
public class WebScrapingController {

    @RequestMapping(value = "/scrap", method = RequestMethod.GET)
    public void webScrap(final HttpServletRequest request, final ModelMap model) throws IOException {
//
//        // 網頁抓取到的內容寫入資料庫
//        webScrapingService.doWebScrapping();
//
//        // 關鍵字
//        String[] keyword = keyWordService.findAll();
//
//        // 資料
//        String[] data = dataContentService.findEverySourceTop5();
//
//        // 串接 openai api
//        String response = openAiGptService.connectOPENAI("",data,keyword);
//
//        // 推播 LINE
//        lineBotService.pushMessageToUser();


    }

    @RequestMapping(value = "/scrapTest", method = RequestMethod.GET)
    public void webScrapTest(final HttpServletRequest request, final ModelMap model) throws IOException {

        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);
            HtmlPage page = webClient.getPage("https://www.wsj.com/finance/stocks?mod=nav_top_subsection");
            String pageHtml = page.asXml();  // 獲取頁面的完整 HTML
            System.out.println("pageHtml>>>>>>" + pageHtml);

//        return "redirect:/home";
        }
    }

}





