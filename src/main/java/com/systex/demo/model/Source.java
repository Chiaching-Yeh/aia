package com.systex.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Source implements Serializable {

    private static final long serialVersionUID = 1L;
    private String type;
    private String userId;
    private String groupId;
    private String roomId;

}
