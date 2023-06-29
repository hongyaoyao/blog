package com.hyy.mapper;

import com.hyy.po.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: TagMapper
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 22:16
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface TagMapper {

    @Insert("INSERT INTO t_tag(name) " +
            "VALUES(#{name})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int saveTag(Tag tag);

    @Select("SELECT * " +
            "FROM t_tag " +
            "WHERE id = #{id}")
    Tag getTag(Long id);

    @Select("SELECT * " +
            "FROM t_tag " +
            "WHERE name = #{name}")
    Tag getTagByName(String name);

    @Select("SELECT * " +
            "FROM t_tag")
    List<Tag> getAllTag();

    @Update("UPDATE t_tag " +
            "SET name = #{tag.name} " +
            "WHERE id=#{tag.id}")
    int updateTag(@Param("tag") Tag tag);

    @Delete("DELETE " +
            "FROM t_tag " +
            "WHERE id = #{id}")
    int deleteTag(Long id);

    @Select("SELECT t.* " +
            "FROM t_tag AS t, t_blog_tag AS bt " +
            "WHERE t.id = bt.tag_id AND bt.blog_id = #{blogId}")
    List<Tag> getTagsByBlogId(Long blogId);

    // 客户端标签页面展示所有标签和每个标签的博客数量
    List<Tag> getBlogsTag();
}
