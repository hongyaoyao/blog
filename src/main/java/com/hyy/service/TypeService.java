package com.hyy.service;

import com.hyy.po.Type;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: TypeService
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 17:05
 * @Emial: 1299694047@qq.com
 */
public interface TypeService {

    List<Type> getAllType();

    Type getType(Long id);

    int saveType(Type type);

    Type getTypeByName(String name);

    int updateType(Type type);

    int deleteType(Long id);

    List<Type> getBlogsType();
}
