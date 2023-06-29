package com.hyy.mapper;

import com.hyy.po.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.mapper
 * @CLASS_NAME: CommentMapper
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/20 15:56
 * @Emial: 1299694047@qq.com
 */
@Mapper
@Repository
public interface CommentMapper {

    // 根据博客Id查询其所属的所有最上层的评论，根据创建时间倒序排列
    @Select("SELECT * " +
            "FROM t_comment " +
            "WHERE blog_id = #{blogId} AND parent_comment_id IS null " +
            "ORDER BY create_time DESC")
    List<Comment> getCommentsByBlogIdAndParentCommentNull(Long blogId);

    // 根据父级评论（评论）的Id查询子级评论（回复评论），此时可以不使用博客Id
    @Select("SELECT * " +
            "FROM t_comment " +
            "WHERE parent_comment_id = #{parentCommentId}")
    List<Comment> getReplyComment(@Param("parentCommentId") Long parentCommentId);

    //添加一个评论
    @Insert("INSERT INTO t_comment(nickname, email, content, avatar, create_time, blog_id, parent_comment_id, admin_comment) " +
            "VALUE(#{comment.nickname}, #{comment.email}, #{comment.content}, #{comment.avatar}, #{comment.createTime}, #{comment.blogId}, #{comment.parentCommentId}, #{comment.adminComment})")
    int saveComment(@Param("comment") Comment comment);

    //根据博客Id删除评论，为了在删除博客时能能删除其所属的评论
    @Delete("DELETE " +
            "FROM t_comment " +
            "WHERE blog_id = #{blogId}")
    Integer deleteCommentByBlogId(Long blogId);

    // 客户端页面底部的评论统计数量
    @Select("SELECT count(*) " +
            "FROM t_comment as c")
    Integer getCommentsCount();
}
