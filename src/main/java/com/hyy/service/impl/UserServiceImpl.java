package com.hyy.service.impl;

import com.hyy.mapper.UserMapper;
import com.hyy.po.User;
import com.hyy.service.UserService;
import com.hyy.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service.impl
 * @CLASS_NAME: UserServiceImpl
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 15:02
 * @Emial: 1299694047@qq.com
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
