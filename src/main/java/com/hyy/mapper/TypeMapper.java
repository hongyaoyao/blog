package com.hyy.mapper;

import com.hyy.po.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: TypeMapper.xml
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 17:07
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface TypeMapper {

    // 查询所有分类
    @Select("SELECT * " +
            "FROM t_type")
    List<Type> getAllType();

    // 根据分类Id查询分类
    @Select("SELECT * " +
            "FROM t_type " +
            "WHERE id = #{id}")
    Type getType(Long id);

    // 增加分类
    @Insert("INSERT INTO t_type(name) " +
            "VALUES(#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int saveType(Type type);

    // 根据分类名称查询分类
    @Select("SELECT * " +
            "FROM t_type " +
            "WHERE name = #{name}")
    Type getTypeByName(String name);

    // 根据已有分类Id更新分类
    @Update("UPDATE t_type " +
            "SET name = #{type.name} " +
            "WHERE id=#{type.id}")
    int updateType(@Param("type") Type type);

    // 根据已有分类Id删除分类
    @Delete("DELETE " +
            "FROM t_type " +
            "WHERE id = #{id}")
    int deleteType(Long id);

    // 客户端分类页面展示所有分类和每个分类的博客数量
   List<Type> getBlogsType();
}
