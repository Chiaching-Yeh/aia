package com.systex.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    private String text;
    private String filename;
    private String filesize;
    private String title;
    private String address;
    private String latitude;
    private String longitude;
    private String packageId;
    private String stickerId;

}
