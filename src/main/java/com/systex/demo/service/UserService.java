package com.systex.demo.service;

import com.systex.demo.dao.KeyWordInterface;
import com.systex.demo.dao.UserInterface;
import com.systex.demo.model.DataContent;
import com.systex.demo.model.KeyWord;
import com.systex.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserInterface userDao;

    @Transactional
    public String[] findAll() {
        List<User> userList = userDao.findALl();

        String[] valuesArray = userList.stream()
                .map(User::getUserid)
                .toArray(String[]::new);

        return valuesArray;
    }

    @Transactional
    public Integer create(User user) {
        return userDao.create(user);
    }

}
