package com.systex.demo.dao;

import com.systex.demo.model.DataContent;
import com.systex.demo.model.KeyWord;
import com.systex.demo.model.User;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.jdbi.v3.core.statement.Query;
import org.jdbi.v3.sqlobject.SqlObject;

import java.util.List;

public interface UserInterface extends SqlObject {

    default List<User> findALl() {
        try (Handle handle = getHandle()) {
            handle.registerRowMapper(BeanMapper.factory(User.class));
            String sql = "SELECT * FROM USER";
            Query query = handle.createQuery(sql);
            return query.mapTo(User.class).list();
        }
    }

    default Integer create(User user) {
        try (Handle handle = getHandle()) {

            String sql = "INSERT INTO USER (USERID) "
                    + "VALUES (:userid)";

            return handle.createUpdate(sql).bindBean(user).execute();
        }
    }



}
