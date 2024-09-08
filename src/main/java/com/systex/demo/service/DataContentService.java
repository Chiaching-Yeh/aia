package com.systex.demo.service;

import com.systex.demo.dao.DataContentInterface;
import com.systex.demo.dao.WebScrapingInterface;
import com.systex.demo.model.DataContent;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DataContentService {

    @Autowired
    public DataContentInterface dataContentDao;


    @Transactional
    protected Integer create(DataContent dataContent) {
        return dataContentDao.create(dataContent);
    }


}

