package com.hyy.service;

import com.hyy.po.User;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: UserService
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 15:01
 * @Emial: 1299694047@qq.com
 */
public interface UserService {

    User checkUser(String username, String password);
}
