package com.hyy.mapper;

import com.hyy.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: UserDao
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 15:05
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE username = #{username} AND password = #{password}")
    User findByUsernameAndPassword(@Param ("username") String username, @Param("password") String password);
}
