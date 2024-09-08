package com.systex.demo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class DataContent {

    int id;
    String dataContentDetail;
    String title;
    String source;
    LocalDateTime createDateTime;

}
