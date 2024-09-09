package com.systex.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systex.demo.dao.DataContentInterface;
import com.systex.demo.model.DataContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class DataContentService {

    @Autowired
    public DataContentInterface dataContentDao;

    @Transactional
    protected Integer create(DataContent dataContent) {
        return dataContentDao.create(dataContent);
    }

    @Transactional
    protected List<DataContent> findAll() {
        return dataContentDao.findAll();
    }

    @Transactional
    public String[] findEverySourceTop5() {

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> jsonStrings = new ArrayList<>();

        List<DataContent> tempList = dataContentDao.findEverySourceTop5();
        for(DataContent dataContent : tempList) {

            HashMap<String, String> map = new HashMap<>();
            map.put("title", dataContent.getTitle());
            map.put("content", dataContent.getDataContentDetail().isEmpty() ?"" : dataContent.getDataContentDetail());

            try {
                // 將Map轉換成JSON
                String json = objectMapper.writeValueAsString(map);
                jsonStrings.add(json);
                jsonStrings.toArray(new String[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return jsonStrings.toArray(new String[0]);
    }


    @Transactional
    public Integer destroy() {
        return dataContentDao.destroy();
    }


}

