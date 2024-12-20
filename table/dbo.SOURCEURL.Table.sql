USE [aia]
GO
/****** Object:  Table [dbo].[SOURCEURL]    Script Date: 2024/11/17 下午 01:14:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SOURCEURL](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[SOURCEURL] [nvarchar](max) NULL,
	[SOURCENAME] [nvarchar](50) NULL,
	[ISACTIVE] [bit] NULL,
 CONSTRAINT [pk_SOURCEURL] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[SOURCEURL] ON 

INSERT [dbo].[SOURCEURL] ([ID], [SOURCEURL], [SOURCENAME], [ISACTIVE]) VALUES (1, N'https://www.wsj.com/finance/stocks?mod=nav_top_subsection', N'華爾街日報', 0)
INSERT [dbo].[SOURCEURL] ([ID], [SOURCEURL], [SOURCENAME], [ISACTIVE]) VALUES (2, N'https://www.nytimes.com/section/business/economy', N'紐約時報', 1)
INSERT [dbo].[SOURCEURL] ([ID], [SOURCEURL], [SOURCENAME], [ISACTIVE]) VALUES (3, N'https://tw.stock.yahoo.com/intl-markets', N'雅虎國際財經', 1)
SET IDENTITY_INSERT [dbo].[SOURCEURL] OFF
GO
