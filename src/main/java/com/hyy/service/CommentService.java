package com.hyy.service;

import com.hyy.po.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: CommentService
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/20 16:55
 * @Emial: 1299694047@qq.com
 */
public interface CommentService {

    List<Comment> getCommentsByBlogId(Long blogId);

    int saveComment(@Param("comment") Comment comment);

    Integer getCommentsCount();
}
