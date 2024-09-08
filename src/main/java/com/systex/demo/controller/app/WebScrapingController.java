package com.systex.demo.controller.app;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.systex.demo.service.WebScrapingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class WebScrapingController {

    @Autowired
    WebScrapingService webScrapingService;

    @RequestMapping(value = "/scrap", method = RequestMethod.GET)
    public String webScrapTest(final HttpServletRequest request, final ModelMap model) throws IOException {
//        webScrapingService.doWebScrapping();
//        try (final WebClient webClient = new WebClient()) {
//            webClient.getOptions().setJavaScriptEnabled(false);
//            webClient.getOptions().setCssEnabled(false);
//            HtmlPage page = webClient.getPage("https://www.nytimes.com/section/business/economy");
//
//            System.out.println("page>>"+page.querySelectorAll("css-18yolpw"));


//            System.out.println("liElements<<"+liElements);

//            // 遍歷每個 <li> 元素
//            for (DomNode liElement : liElements) {
//                // 在當前的 <li> 元素中，查找標題 <h3>
//                HtmlElement titleElement = liElement.querySelector(".css-14ee9cx article.css-1l4spti a.css-8hzhxf h3.css-1j88qqx.e15t083i0");
//                if (titleElement != null) {
//                    String titleText = titleElement.getTextContent();
//                    System.out.println("Title: " + titleText);
//                } else {
//                    System.out.println("Title not found in this li element.");
//                }
//
//                // 在當前的 <li> 元素中，查找內文 <p>
//                HtmlElement contentElement = liElement.querySelector(".css-14ee9cx article.css-1l4spti p.css-1pga48a.e15t083i1");
//                if (contentElement != null) {
//                    String contentText = contentElement.getTextContent();
//                    System.out.println("Content: " + contentText);
//                } else {
//                    System.out.println("Content not found in this li element.");
//                }
//            }

//        }
        return "index";
    }

}
