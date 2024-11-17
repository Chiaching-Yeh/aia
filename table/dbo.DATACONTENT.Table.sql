USE [aia]
GO
/****** Object:  Table [dbo].[DATACONTENT]    Script Date: 2024/11/17 下午 01:14:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DATACONTENT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[TITLE] [nvarchar](max) NOT NULL,
	[DATACONTENTDETAIL] [nvarchar](max) NOT NULL,
	[CREATEDATETIME] [datetime] NOT NULL,
	[SOURCE] [nvarchar](50) NOT NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[DATACONTENT] ON 

INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (581, N'What’s Next for Rate Cuts? The Fed Is Watching Jobs and Prices.', N'A Federal Reserve official predicted quarter point rate cuts if data looked ‘fine’. But he also set out a scenario for a pause — or faster reductions.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (582, N'Interest Rates Fall, but Central Banks Are No Longer in Lock Step', N'Officials in some countries started cutting rates last year, but others, including those in Europe and the United States, have taken a more cautious approach.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (583, N'After Fed Cuts Rates, Biden Claims Credit for Economy’s Strength', N'The president said he was not declaring victory over inflation, but marking a pivot point for the recovery from pandemic recession.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (584, N'America’s Inflation Fight Is Ending, but It’s Leaving a Legacy', N'As inflation cools and the Federal Reserve cuts rates, an era of economic upheaval is coming to a close, but not without lingering marks.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (585, N'Federal Reserve Cuts Interest Rates for the First Time in Four Years', N'Jerome H. Powell, the Fed chair, said that the central bank would take future interest rate cuts “meeting by meeting” after lowering rates by a half percentage point, an unusually large move.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (586, N'Boeing to Begin Temporary Layoffs Because of Strike', N'The aerospace giant said it would temporarily lay off tens of thousands of employees to stem losses from a walkout by the machinists’ union.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (587, N'What Trump Has Said About Rates, and Why It Matters', N'He has suggested that presidents should “have a say” on interest rates, though he later walked the comment back.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (588, N'Cheaper Mortgages and Car Loans: Lower Rates Are on the Horizon', N'With the Federal Reserve lowering interest rates, some borrowing costs have begun to ease — signs that loans may become cheaper in the near future.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (589, N'A Fed Rate Cut Would Cap a Winning Streak for Biden and Harris on Prices', N'Improved data on borrowing costs and price growth has buoyed consumers, but it might be coming too late to significantly affect the presidential race.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (590, N'The Fed Makes a Large Rate Cut and Forecasts More to Come', N'Fed officials kicked off rate cuts with a half-point reduction, confident that inflation is cooling and eager to keep the job market strong.', CAST(N'2024-09-21T15:01:49.517' AS DateTime), N'紐約時報')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (591, N'保守以對！日銀宣布基準利率維持不變', N'即時中心／林耿郁報導眾所矚目的美國聯準會FED宣布降息2碼後，各國央行也紛紛出抬自己的經濟與利率政策；台灣央行宣布維持基準利率2%不變、升準1碼，並加碼打房政策；至於7月份引發全球市場動盪的日本銀行，經過兩天會議後，宣布基本利率維持在0.25%不變，符合市場預期。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (592, N'《國際政治》美國和伊拉克達成協議 在2026年底前撤出美軍', N'【時報-台北電】美國和伊拉克已就美軍和其他外國軍隊在2026年底前撤出伊拉克達成協議，這是美國為削減長達20年之久的軍事承諾所做的最新嘗試。官員們說，駐紮在巴格達、伊拉克西部和該國其他地區的數以千計的美軍和聯軍將在明年9月前撤離，隨後在次年年底前對伊拉克北部城市艾比爾的部隊進行縮編。 據法廣報導，《華爾街日報》援引美國國防官員指出，一名拜登政府高級官員20日表示，撤軍計畫的大綱已完成，但一些最終細節仍有待敲定，特別是與聯軍其他成員的細節。美軍官員說，該協議可能在下周公開宣布。官員們說，即使在2026年之後，根據新的雙邊安全協議，一支小型美軍部隊仍可能以顧問身分留駐伊拉克，並為駐敘利亞的美軍提供後勤支持。 美國在伊拉克駐軍約2500人，在鄰國敘利亞駐軍900人，以防止恐怖組織「伊斯蘭國」（ISIS）捲土重來。伊斯蘭國曾占大片伊拉克領土，直到2019年被美國、伊拉克和其他外國軍隊組成的聯軍基本擊敗。 2003年至2011年期間，美軍駐紮在伊拉克的人數要多得多。在親伊朗民兵組織要求撤走美軍的壓力下，伊拉克總理蘇達尼（Mohammed Shia al-Sudani）自2022年上任以來一直呼籲', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (593, N'歐盟以俄被凍結資產利息向烏克蘭放貸350億歐元', N'正在烏克蘭訪問的歐盟執委會主席范德賴恩20日表示，歐盟將向烏克蘭提供350億歐元貸款。俄外交部發言人札哈羅娃表示，歐盟將被凍結俄羅斯資產所獲收益用於向烏克蘭提供武器，意味著歐盟已完全失去獨立性。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (594, N'美股道瓊創高科技股下挫 法人：台股關注量能和季線反壓', N'（中央社記者鍾榮峰台北2024年9月21日電）美股道瓊再創歷史新高，科技股震盪走跌，台股20日漲幅收斂季線得而復失，營建股受打炒房政策衝擊，合計上市櫃超過20檔跌停，投信連25日買超台股，外資在台指期增加淨空單至3.19萬口。法人表示，大盤季線仍下彎，後續留意量能可否續放大，營建股短線承壓。美股今天漲跌互見，道瓊工業指數續創歷史新高，道瓊工業指數終場上漲38.17點，或0.09%，收在42063.36點，標準普爾500指數下跌11.09點，或0.19%，收在5702.55點。科技股為主的那斯達克指數震盪下跌65.66點，或0.36%，收在17948.32點，費城半導體指數下跌66.582點，或1.31%，收在5000.066點。人工智慧AI晶片大廠輝達（NVIDIA）下跌1.59%收116美元，台積電美國存託憑證（ADR）跌1.21%收174.08美元。台股加權指數20日漲幅收斂，終場上漲116.73點，收在22159.42點，季線約22335點得而復失，成交值新台幣4614.54億元。政策加重打炒房力道衝擊，上市櫃營建股合計22檔跌停。三大法人合計買超269.79億元，其中自營商賣超', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (606, N'美股開低費半走弱 台指期夜盤小回', N'市場對美國聯準會（Fed）降息2碼的慶賀行情大致反應完畢，美股20日轉趨震盪，四大指數集體開低，其中費半指數跌勢相對沉重、下挫逾1％，台指期夜盤小跌22點，暫報22,194點。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (595, N'《國際產業》陸市賣不動 賓士再砍財測', N'【時報-台北電】德國豪華汽車品牌賓士（Mercedes-Benz）以整體環境進一步惡化、特別是中國市場持續不振，大砍今年全年財測。由於這是賓士不到兩個月時間二度調降獲利前景，股價20日應聲暴跌逾7％至近兩年多最低，為歐洲汽車股當中跌幅最深的個股。 賓士表示，目前預計2024年經調整後的銷售回報率（ROS）將介於7.5％至8.5％之間，低於先前預測的10％至11％。這也意味今年下半年的ROS只有6％。另外它還提到，今年息稅前獲利（EBIT）也將明顯低於去年的197億歐元。 該豪車集團把此歸咎於中國市場，它指稱該國經濟成長持續失去動能，消費疲弱不振與房地產業持續低迷，導致其銷售受到影響。 這也是賓士自7月來以中國銷售不佳為由，第二度調降今年財測。 執行長凱倫涅斯（Ola Kaellenius）稍早在電話會議上向分析師表示，由於中國經濟仍不見曙光，民眾大幅削減購買昂貴資本品的支出。他無法預測該情況將持續多久，只表示在可預見未來，對中國市場抱持謹慎態度。 不僅只有賓士遭遇麻煩，歐洲最大車廠福斯汽車（Volkswagen）在9月初也宣布，為撙節資本支出，將考慮關閉德國境內工廠，創下該汽車公司成立', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (596, N'《國際產業》砸近960億抗衡中國 美擬提供14州電動車電池生產獎勵', N'【時報-台北電】中國電動車和其他電子產品、電池持續擴展其在全球主導的地位，美國拜登政府宣布，將向美國公司提供超過30億元（美元，下同，約合新台幣959億元）的獎勵資金，以促進用於電動車的先進電池和其他材料的國內生產。該筆補助將資助14個州的總共25個項目。 據美聯社消息，20日宣布的贈款是根據2021年批准、兩黨支持的《基礎設施法》進行的第2輪電動汽車電池資金。前一輪資金為14個正在進行的項目撥款18億元。這些總額低於官員在2022年10月宣布的金額，反映了美國官員在有時是漫長的談判中撤回或拒絕的一些項目。 報導指，得到該補助的公司，將用於加工鋰、石墨或其他電池材料，或製造用於電動車電池的組件。獲補助的14個州，包括密西根州和北卡羅來納州等選戰必爭之州，以及俄亥俄州、德州、南卡羅來納州和路易斯安那州。 美國白宮經濟顧問布雷納德（Lael Brainard）19日在白宮電話會議上表示，獎勵項目使我們更接近實現行政當局的目標，也就是在美國建立電池和關鍵礦物的「端到端供應鏈」，從採礦到加工再到製造和回收，「這對於減少中國在這一關鍵領域的主導地位至關重要」。 布雷納德續指，近年來，中國壟斷了鋰', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (597, N'《國際產業》Nike換帥救業績 股價歡呼', N'【時報-台北電】運動品牌龍頭耐吉（Nike）為了救業績，19日宣布換帥，現任執行長唐納修（John Donahoe）掌舵近五年後退休，由前高層希爾（Elliott Hill）回鍋，接任總裁兼執行長。 市場對此一片看好，耐吉股價在20日早盤開高大漲8％，但該股今年以來市值已經縮水超過四分之一。 耐吉在6月下旬公布的業績不如預期，大砍全年財測，並預估當季銷售額將縮減10％。這是疫情首年和2008～2009年金融危機以外，該公司20多年來最糟的業績。 業績衰退幅度遠超乎市場預期，儘管耐吉聯合創辦人力挺，唐納修仍面臨龐大壓力。 唐納修上任時帶領公司轉戰電子商務，加上搭上疫情，當時全年銷售額從2019財年的391億美元，攀升至2024財年的514億美元。然而隨著疫情結束，線上銷售額也隨之滑落。 當時耐吉試圖擺脫批發商的合作夥伴關係，然而也因此On Running和Hoka等競爭對手取得空隙，搶占貨架及市占率。 唐納修坦承決策失誤，並在去年12月宣布大規模重組計劃，未來三年內削減約20億美元的成本。其中，將裁員約2％即1,500多人，以將資金集中投入跑步、女裝和Jordan品牌等具成長潛力的業務。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (598, N'《國際產業》Fed降息激勵 特斯拉飆漲', N'【時報-台北電】美國聯準會（Fed）啟動降息循環有望讓汽車股大幅受惠！因隨利率下滑，車貸利息支出減少，將刺激消費者購車意願。受此影響，電動車大廠特斯拉股價19日強漲超過7％，表現亮眼。 特斯拉股價19日收高7.4％，大幅超出標準普爾500指數的1.7％漲幅。通用與福特汽車股價當天多數時間也都走高，但終場漲幅因遭侵蝕、反而分別收低0.1％與0.6％。Stellantis股價則上漲1.4％。 Future Fund Active ETF聯合創辦人兼特斯拉股東布萊克（Gary Black）認為，降息對於長期持有高本益比的成長股股票，例如特斯拉，能帶來更大的正面影響。 利率降低除了有助減輕消費者的購車壓力，還能緩解車商降價壓力與刺激市場需求。Cars Commerce產業數據部門主管林蘭（Rebeccan Lindland）19日發布新聞稿指出，在Fed降息刺激下，消費者購車意願增加，也讓汽車市場朝更可預測的方向發展。這一發展對於面臨銷售困境的特斯拉格外重要。它在今年上半年共售出約83.1萬輛車，較去年同期下滑約7％。由於買氣不振，特斯拉股價今年以來重挫約7％。對特斯拉而言，它的商業模式主要依', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (599, N'《國際金融》以軍空襲黎巴嫩 金價再攻頂', N'【時報-台北電】以色列19日深夜針對黎巴嫩南部的真主黨目標發動一波猛烈空襲，寫下加薩戰爭爆發一年來最密集的空襲行動。美國五角大廈擔心，雙方接下來可能在黎巴嫩展開地面戰爭。中東緊張情勢升溫帶動金價勁揚，現貨金價升破每盎司2,600美元大關，再創新高紀錄。 現貨金價20日盤中大漲1％，報每盎司2,613.38美元，紐約12月期金揚升0.8％，報每盎司2,635.5美元，刷新歷史高點紀錄。金價正朝著周線收紅的方向前進。 地緣政治不確定性增加避險資產黃金的吸引力，且美國聯準會（Fed）降息對沒有孳息的黃金也是一大利多，為金價帶來支撐。聯準會18日宣布降息2碼，幅度大於多數分析師預期，聯準會並預測今年底前將再降2碼，2025年調降4碼，2026年再降息2碼。 線上交易商Capital.com金融市場分析師羅達（Kyle Rodda）表示，「當前的趨勢對黃金非常有利，倘若這些有利的市場條件能夠延續下去，金價有可能在未來12個月升抵每盎司2,600美元至2,800美元區間。」 信評機構惠譽旗下研究部門BMI在報告中指出，「基於美元走貶和美債殖利率下滑，再加上地緣政治緊張升溫，預期金價在未來幾個月將能', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (600, N'《國際產業》缺席Twitter收購案證詞　SEC尋求制裁馬斯克', N'【時報-台北電】美國證券交易委員會（SEC）週五宣布決定對億萬富豪馬斯克（Elon Musk）採取制裁措施。此舉源於馬斯克未能按計畫出席作證，這是SEC調查其以440億美元收購社群媒體平台Twitter（現改名為X）一案的一部分。 根據最近的一份法庭文件，SEC正尋求法院命令，要求馬斯克就未能出席作證一事提供合理解釋，否則將被視為藐視法庭。 SEC的這一舉動表明，對這起備受關注的收購案的監管審查可能會升級。 馬斯克的法律團隊對SEC的意圖作出回應，稱潛在的製裁措施「過於嚴厲」且不必要。他們表示馬斯克的證詞已重新安排，暗示缺席作證不應引發如此嚴厲的反應。 SEC對馬斯克的Twitter收購案的調查一直在進行，委員會正在審查收購過程的各個方面。 馬斯克未能出席的作證是這項調查的一部分，現在看來，隨著制裁威脅的出現，調查似乎正在升級。（新聞來源：中時新聞網 楊文琪）', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (601, N'《商情》白銀漲近兩個月最高 黃金再創歷史新高', N'【時報-台北電】綜觀近一周紐約COMEX貴金市場漲跌互見，其中貴金屬之一的白銀表現亮眼，並於周四(9/19)漲破1000美元大關，白銀今年以來上漲三成，受惠國際科技產業需求激增，黃金在美國降息兩碼後還會大漲等緣故，分析師表示，許多市場已逐漸轉往購買白銀，甚至用美元現金搶佔白銀現貨，獲利約兩成，另外，目前白銀的價格還處於被嚴重低估的水位，包括中國工業開始復甦，白銀用在電動車、太陽能模組以及通訊產品上，都有工業的作用，再加上印度人因為現在黃金價格太貴轉買白銀，進而提升珠寶銀飾需求，白銀截至周四報31.42美元。 黃金市場漲跌互見，上周五(9/13)紐約期金漲逾1%，因市場對美國聯準會(Fed)即將降息的期望推高，加上美元走軟，增強投資人以美元計價的黃金更具便宜及吸引力，不過周一(9/16)至周二(9/17)紐約期金下挫近1%，並為連兩個交易日下跌，因美國8月零售銷售意外走揚0.1%，推升美元上漲，削弱投資人以美元計價的黃金更顯昂貴及失去吸引力，然而到了周三(9/18)至周四(9/19)紐約期金連兩紅，並於周四創下歷史新高，係因受到Fed降息兩碼的推助之下以及央行購買黃金的強勁需求與地緣政治', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (602, N'《國際社會》呼叫器爆炸案 保加利亞銷售人失聯', N'【時報-台北電】黎巴嫩真主黨成員的呼叫器（BB call）與對講機連續驚傳大爆炸，兩起事件釀至少37人死亡，近3000人受傷。不過，呼叫器的產地仍然未明，有消息指出保加利亞的空殼公司Norta Global Ltd疑似涉事，但該公司持有人、39歲的印度裔挪威人侯賽（Rinson Jose）在爆炸案當天即失蹤，至今仍下落不明。 貼有台灣廠商「金阿波羅」標籤的呼叫器產地源頭，至今仍是一個謎團。金阿波羅集團18日澄清，事發的AP 924型號呼叫器是由匈牙利合作夥伴BAC諮詢有限公司（BAC Consulting KFT）生產後，再貼上金阿波羅授權的商標銷售，並非金阿波羅公司製造。 BAC公司地址在匈牙利首都布達佩斯郊區的住宅區內，但未設有實體辦公室。「BBC事實查核」調查發現，在BAC公司網站上，僅顯示執行長巴索尼–艾齊迪亞科諾（Cristiana Barsony-Arcidiacono）1人的名字。巴索尼–艾齊迪亞科諾告訴NBC，公司與金阿波羅公司有合作，她稱自己只是中間人，並未生產呼叫器。但其母稱她受到不明威脅，目前接受匈牙利情報機構保護。 匈牙利政府19日也澄清，該國沒有製造或出口呼叫', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (603, N'《美股》收盤速報：道瓊再創新高 傳高通有意收購英特爾', N'【時報-台北電】聯準會啟動降息循環後，市場正在評估FED的下一步行動，美股周五三大指數漲跌幅不大，道瓊收盤再創歷史新高，費半指數則跌逾1.3%。 三大指數周線全數收紅，道瓊累計上漲1.61%，標普上漲1.36%，那斯達克指數漲1.49%。費半也再微漲約0.4%。 周五恰逢「三巫日」，約5.1兆美元的指數、股票和ETF期權同日到期，交易量超過200億股，創下2021年1月以來最大量。 個股方面，傳出晶片大廠高通(QCOM)向競爭對手英特爾(INTC)提出收購方案。高通收盤下挫2.87%；英特爾股價盤中一度飆升近9.5%，終場收漲3.31%。英特爾和高通對此消息均未作評論，這筆交易若成，將會成為半導體產業史上最大規模的收購案之一。 聯邦快遞警告業務放緩，股價暴跌逾15%。聯邦快遞上季獲利大幅下滑，同時調降全年營收預估，因高利潤率快遞服務需求減少。 另外，運動品牌Nike收紅6.85%。Nike宣布，行政總裁John Donahoe將退休，由前高層Elliott Hill接任。 聯準會官員發言成為市場關注的焦點，聯準會理事鮑曼(Michelle Bowman)在本次FOMC會議中反對降息2碼', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (604, N'【美股盤後】互有漲跌 道指續創新高', N'（中央社台北2024年9月21日電）美股今天互有漲跌，道瓊工業指數續創歷史新高。道瓊工業指數終場上漲38.17點，或0.09%，收在42063.36點。標準普爾500指數下跌11.09點，或0.19%，收在5702.55點。以科技股為主的那斯達克指數下跌65.66點，或0.36%，收在17948.32點。費城半導體指數下跌66.582點，或1.31%，收在5000.066點。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (605, N'降息後樂觀情緒暫歇 美股開盤小跌', N'（中央社紐約2024年09月20日綜合外電報導）美國聯邦準備理事會（Fed）本月18日大幅調降基準利率2碼，激勵美股昨天大漲，但美股主要指數今天開盤小跌，投資人持觀望態度。道瓊工業指數開盤下跌65.8點或0.16%，報41959.43點。標普500指數開盤下降4.0點或0.07%，報5709.64點。那斯達克指數開盤微跌14.6點或0.08%，報17999.34點。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (607, N'聚和國際完成8.32億元現增 屏東新廠拚2027年完工', N'（中央社記者潘智義台北2024年09月20日電）聚和國際(6509)今天表示，完成上櫃以來首次現金增資，募資總金額新台幣8.32億元，增資新股於今天上櫃掛牌交易。此外，擬投入35億元於屏東科學園區擴建新廠，並預計2027年完工啟用。聚和國際上櫃以來首次現金增資，已順利於13日募集完成，本次現金增資普通股2萬6000張，每股定價32元，募資總金額8.32億元。聚和國際表示，國科會主導開發的屏東科學園區規劃「智慧農醫」計畫，為因應歐美客戶對生物緩衝劑等相關需求成長趨勢，且在重視企業社會責任使命下，積極部署減廢與節能的產品製程技術，以符合ESG（環境保護、社會責任、公司治理）趨勢，擬投入35億元於屏東科學園區擴建新廠，並預計2027年完工啟用。聚和國際指出，長期積極耕耘生物緩衝劑市場，已通過多家國際知名藥廠、隱形眼鏡清潔液及保養品廠商驗證，並於今年7月取得EXCiPACT GMP國際認證，帶動生物緩衝劑出貨穩定成長。預計投入屏科新廠1期佔地約6公頃。聚和國際說明，公司成立初始生產造紙用化學品，迄今已涵蓋特用化學品、精密化學品及N次貼系列產品，從台灣鳳山廠建廠開始，陸續至印尼、中國設廠，並成立', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (608, N'《國際產業》賓士再度下調財測 股價暴跌近8%', N'【時報編譯張朝欽綜合外電報導】賓士汽車(Mercedes-Benz)在不到兩個月的時間裡，第二次下調全年獲利率目標，也打擊了其它德國汽車製造商，他們都將原因歸咎於中國市場的疲軟。 賓士在當地周四晚間宣布獲利預警，隨後股價跌到近兩年新低，並成為歐洲汽車股跌幅最深的股票。 賓士在法蘭克福上市的股票目前跌約6.8%，稍早一度重挫8%。 中國的經濟疲軟，以及當地房地產危機，嚴重打擊了汽車需求，不僅讓賓士感到頭疼，也讓福斯、保時捷和BMW感到頭疼。 賓士在七月也以同樣的中國理由，下調今年利潤率目標。 執行長凱倫涅斯(Ola Kaellenius)在週四的電話會議上對分析師說：「我試圖以外交辭令的方式說，謹慎是非常巨大的」。他補充說，在如此環境下，削減購買昂貴資本品的支出，並不令人感到意外。 「這種情況會持續多久？我不知道，但在可預見的未來，我對中國仍會保持謹慎」。 賓士現在預計，2024年調整後的銷售回報率(ROS)將在7.5%至8.5%之間，低於此前預估的10%至11%，也就是說，今年下半的銷售回報率將在6%左右。 因此，賓士今年的息稅前獲利(EBIT)，預計將遠低於去年的197億歐元。 加拿', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (609, N'《港股》恆指收高245點 黃金閃亮飆漲', N'【時報編譯張朝欽綜合外電報導】中國人行在中午宣布維持基準貸款利率不變，大陸股市漲跌互見。人民幣升值至近16個月以來的最高水準，導致國有銀行介入，防止人民幣升值過快。 周五香港恆生綜合指數收盤上漲245.41點或1.36%，為18258.57點。國企股(H股)指數升1.2%，為6382點。科技指數漲1.4%，為3704點。交易所成交金額來到約1350億港元。 除了騰訊小跌0.1%，主要網路科技股全面上揚。 歐盟推遲對中國電動車關稅的表決，並考慮「最低價格」方案。汽車走高，小鵬升6.9%，理想漲2.8%，蔚來獲得大摩維持增持評級，升2.4%。 金價首次突破2600美元，黃金股持續強勁，上漲3.3%，為表現最好板塊。招金礦業升3.6%，山東黃金漲3.9%。 快手收跌1.5%，此前有消息指出，大股東DCM折價4%，賣股套現37億港元。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
INSERT [dbo].[DATACONTENT] ([ID], [TITLE], [DATACONTENTDETAIL], [CREATEDATETIME], [SOURCE]) VALUES (610, N'《國際金融》聯準會降息2碼 專家：自相矛盾、政策失誤', N'【時報編譯柯婉琇綜合外電報導】MRB Partners周四警告，聯準會在4年多來的首次降息就一口氣調降50個基點，這和該央行所說的「美國經濟情況良好、就業市場穩健」顯然自相矛盾。如果經濟良好帶動需求持續升高，進而重新點燃通膨，那麼這次降息2碼的決定可能成為另一個「政策失誤」。 MRB Partners在報告中指出，可能面臨的風險在於聯準會恐被迫在今年晚些時候或2025年讓降息政策退場，類似於該央行在2021年犯下的政策錯誤。 報告指出，過去聯準會啟動降息周期時，美國國家經濟研究局（NBER）的商業周期指標會顯示經濟陷入低迷，而這次在「經濟良好」的情況下大幅降息，這個決定實在不尋常。基於美國經濟的韌性，聯準會這次激進的降息動作顯得有些為時過早。 其分析師表示，可能重新點燃通膨的因素包括：勞動力市場仍然緊俏將推動薪資增長、供應鏈的挑戰仍然存在、地緣政治緊張局勢影響大宗商品價格，以及財政激勵措施的滯後效應。', CAST(N'2024-09-21T15:01:50.610' AS DateTime), N'雅虎國際財經')
SET IDENTITY_INSERT [dbo].[DATACONTENT] OFF
GO
