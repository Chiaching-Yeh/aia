package com.systex.demo.service;

import com.systex.demo.dao.KeyWordInterface;
import com.systex.demo.model.KeyWord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class KeyWordService {

    @Autowired
    KeyWordInterface keyWordDao;

    @Transactional
    public String[] findAll() {
        List<KeyWord> keyWordList = keyWordDao.findALl();

        String[] valuesArray = keyWordList.stream()
                .map(KeyWord::getKeyword)
                .toArray(String[]::new);

        return valuesArray;
    }

}
