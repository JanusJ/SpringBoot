package com.jane.service.impl;

import com.jane.dao.UserRepository;
import com.jane.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Janus on 2018/9/10.
 */
@Service
public class UserServiceImpl implements UserServcie{

    @Autowired
    UserRepository userRepository;

    @Transactional
    public int updateEmailById(Integer id, String email) {
        System.out.println("进入Service方法。。。");
        int i = userRepository.updateEmailById(id, email);
        return i;
    }
}
