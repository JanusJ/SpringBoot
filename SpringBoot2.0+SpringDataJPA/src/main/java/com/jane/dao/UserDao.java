package com.jane.dao;

import com.jane.model.User;

/**
 * Created by Janus on 2018/9/10.
 */
public interface UserDao {

    User getUserById(Integer id);
}
