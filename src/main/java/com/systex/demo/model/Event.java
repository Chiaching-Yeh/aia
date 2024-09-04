package com.systex.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Event implements Serializable  {

    private static final long serialVersionUID = 1L;

    private String replyToken;
    private String type;
    private Source source;
    private String timestamp;
    private Message message;

}
