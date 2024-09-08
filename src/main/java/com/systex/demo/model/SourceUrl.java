package com.systex.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SourceUrl {

    int id;
    String sourceUrl;
    String sourceName;
    boolean isActive;

}
