USE [aia]
GO
/****** Object:  Table [dbo].[SPRING_SESSION_ATTRIBUTES]    Script Date: 2024/11/17 下午 01:14:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SPRING_SESSION_ATTRIBUTES](
	[SESSION_PRIMARY_ID] [char](36) NOT NULL,
	[ATTRIBUTE_NAME] [varchar](200) NOT NULL,
	[ATTRIBUTE_BYTES] [image] NOT NULL,
 CONSTRAINT [SPRING_SESSION_ATTRIBUTES_PK] PRIMARY KEY CLUSTERED 
(
	[SESSION_PRIMARY_ID] ASC,
	[ATTRIBUTE_NAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[SPRING_SESSION_ATTRIBUTES]  WITH CHECK ADD  CONSTRAINT [SPRING_SESSION_ATTRIBUTES_FK] FOREIGN KEY([SESSION_PRIMARY_ID])
REFERENCES [dbo].[SPRING_SESSION] ([PRIMARY_ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SPRING_SESSION_ATTRIBUTES] CHECK CONSTRAINT [SPRING_SESSION_ATTRIBUTES_FK]
GO
