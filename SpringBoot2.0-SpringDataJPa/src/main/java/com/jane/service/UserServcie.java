package com.jane.service;

import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by Janus on 2018/9/10.
 */
public interface UserServcie {

//    @Transactional
    int updateEmailById(Integer id,String email);
}
