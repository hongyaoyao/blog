package com.hyy.service.impl;

import com.hyy.mapper.TypeMapper;
import com.hyy.po.Type;
import com.hyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: TypeServiceImpl
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 17:06
 * @Emial: 1299694047@qq.com
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;
    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.getType(id);
    }

    @Override
    public int saveType(Type type) {
        return typeMapper.saveType(type);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }

    public List<Type> getBlogsType(){
        return  typeMapper.getBlogsType();
    }
}
