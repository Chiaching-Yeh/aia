package com.systex.demo.service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.systex.demo.dao.WebScrapingInterface;
import com.systex.demo.model.DataContent;
import com.systex.demo.model.SourceUrl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class WebScrapingService {

    @Autowired
    public WebScrapingInterface WebScrapingDao;

    @Autowired
    public DataContentService dataContentService;

    @Transactional
    public void doWebScrapping() {

        List<SourceUrl> sourceUrls = findAll();

        try (final WebClient webClient = new WebClient()) {
            // 禁用 JS 和 CSS
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            for(SourceUrl source : sourceUrls){
                HtmlPage page = webClient.getPage(source.getSourceUrl());

                LocalDateTime current = LocalDateTime.now();

                switch (source.getSourceName()) {
//                    case "華爾街日報":
//                        List<HtmlDivision> items = page.getByXPath("//div[@class='css-bdwau6']//div[@class='css-4neukq']//div[@class='css-bdm6mo']//div[@class='e1sf124z9']");
//
//                        if (items != null && !items.isEmpty()) {
//                            for (HtmlDivision item : items) {
//                                // 查找 headerDiv 標題
//                                String wallStreetContent = "";
//                                String wallStreetTitle = "";
//                                HtmlDivision headerDiv = item.getFirstByXPath(".//div[@class='e1sf124z9 css-ga15cm-HeaderBlock']");
//                                if (headerDiv != null) {
//                                    String titleText = headerDiv.getTextContent();
//                                    if (!titleText.isEmpty()) {
//                                        wallStreetTitle = titleText;
//                                        System.out.println("華爾街日報 Title: " + titleText);
//                                    }
//                                } else {
//                                    System.out.println("華爾街日報 headerDiv Element not found");
//                                }
//
//                                // 查找 contentDiv 內容
//                                HtmlDivision contentDiv = item.getFirstByXPath(".//div[@class='css-1f38a5e']");
//                                if (contentDiv != null) {
//                                    String contentText = contentDiv.getTextContent();
//                                    if (!contentText.isEmpty()) {
//                                        wallStreetContent = contentText;
//                                        System.out.println("華爾街日報 Content: " + contentText);
//                                    }
//                                } else {
//                                    System.out.println("華爾街日報 contentDiv Element not found");
//                                }
//
//                                if (!wallStreetContent.isEmpty()) {
//                                    dataContentService.create(param(wallStreetTitle, wallStreetContent, current, source.getSourceName()));
//                                }
//                            }
//                        }
//
//
//                        break;

                    case "紐約時報":

                        String pageHtml = page.asXml();  // 獲取頁面的完整 HTML
                        Document doc = Jsoup.parse(pageHtml);
                        // 選取class為"css-18yolpw"的所有<li>元素
                        Elements listItems = doc.select("li.css-18yolpw");
                        for (Element listItem : listItems) {
                            String nyTimeTitle = "";
                            String nyTimeContent = "";
                            // 找到<h3>的標題
                            nyTimeTitle = listItem.select("h3.css-1j88qqx").text();
                            // 找到第一個<p>作為內文
                            nyTimeContent = listItem.select("p.css-1pga48a").text();

                            if (!nyTimeTitle.isEmpty()) {
                                dataContentService.create(param(nyTimeTitle, nyTimeContent, current, source.getSourceName()));
                            }


                        }

                        break;

                    case "雅虎國際財經":

                        // 查找所有不包含廣告的 <li> 元素
                        String yahooRawData = "//li[contains(@class, 'js-stream-content') and not(.//div[contains(@class, 'native-ad-item')])]";

                        // 获取所有符合条件的 <li> 元素列表
                        List<HtmlElement> yahooItems = page.getByXPath(yahooRawData);

                        if (yahooItems != null && !yahooItems.isEmpty()) {
                            for (HtmlElement item : yahooItems) {
                                String yahooContent = "";
                                String yahooTitle = "";

                                HtmlAnchor titleAnchor = item.getFirstByXPath(".//h3[contains(@class, 'Mt(0)')]/a");
                                if (titleAnchor != null) {
                                    yahooTitle = titleAnchor.getTextContent();
                                    if (!yahooTitle.isEmpty()) {
                                    } else {
                                        //
                                    }
                                }

                                // 获取 <p> 标签的文本 (内文)
                                HtmlElement paragraph = item.getFirstByXPath(".//p[contains(@class, 'Fz(16px)')]");
                                if (paragraph != null) {
                                    yahooContent = paragraph.getTextContent();
                                    if (!yahooContent.isEmpty()) {
                                    } else {
                                        //
                                    }
                                }
                                if (!yahooTitle.isEmpty()) {
                                    dataContentService.create(param(yahooTitle, yahooContent, current, source.getSourceName()));
                                }
                            }
                        }


                        break;
                    default:
                        System.out.println("nothing matched");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected static DataContent param(String title, String content, LocalDateTime current, String source){
        DataContent dataContent = new DataContent();
        dataContent.setTitle(title);
        dataContent.setDataContentDetail(content);
        dataContent.setCreateDateTime(current);
        dataContent.setSource(source);
        return dataContent;
    }


    protected List<SourceUrl> findAll() {
        return WebScrapingDao.findALl();
    }


}
