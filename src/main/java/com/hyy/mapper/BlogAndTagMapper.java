package com.hyy.mapper;

import com.hyy.po.BlogAndTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: BlogAndTagMapper
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/15 15:47
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface BlogAndTagMapper {

    @Insert("INSERT INTO t_blog_tag(blog_id, tag_id) " +
            "VALUES(#{blogId}, #{tagId})")
    int saveBlogAndTag(@Param("blogId") Long blogId, @Param("tagId")Long tagId);

    @Select("DELETE " +
            "FROM t_blog_tag " +
            "WHERE blog_id = #{blogId}")
    BlogAndTag deleteBlogAndTag(Long blogId);
}
