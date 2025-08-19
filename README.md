# 專案簡介
AI跨境新聞快報資訊推播系統是一個基於 Java 開發的自動化財經資訊處理平台，整合網頁爬蟲、OpenAI API 與 LINE 推播功能，為用戶提供即時、精準的財經市場分析與推送服務。

![pipeline](/doc/aia_pipeline.png)

---

## 核心功能

### 網站資料抓取模組
- 使用 Java 開發的網頁爬蟲系統
- 自動抓取指定財經新聞網站資料

### 資料處理與分析模組
- 結構化存儲：將非結構化資料轉換為結構化格式

### OpenAI 智能分析模組
- 關鍵字分析：利用預設關鍵字進行內容結構化
- 智能摘要：透過 OpenAI API 生成精準內容摘要

### LINE 推播服務模組
- 即時推送：整合 LINE Messaging API

[專案詳細介紹](/doc/AI跨境新聞快報.pdf) 

#### 補充

- 本機器測試請搭配OPENAIKEY 
- 本地測試請使用ngrok將localhost服務對外，需產生對應url並更新至lineOA webhook
- 以下是使用ngrok 的步驟：
1. 下載並安裝ngrok:
   前往 ngrok 官方網站 下載適合你操作系統的版本，並解壓縮。
2. 註冊並取得Authtoken:
   在ngrok 官網註冊帳號，並取得你的Authtoken。
3. 設定Authtoken:
   打開終端機，進入ngrok 資料夾，執行 ngrok authtoken <你的Authtoken>。
4. 啟動ngrok:
   執行 ngrok http <port>，其中 <port> 是你本地服務器正在監聽的埠號。 例如，如果你的服務器在8000 埠，就執行 ngrok http 8000。
5. 取得公開網址:
   ngrok 會在終端機上顯示一個公開的網址（例如 https://xxxx.ngrok.io），這個網址就是你可以用來訪問你本地服務器的網址。