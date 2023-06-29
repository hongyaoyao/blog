package com.hyy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.po
 * @CLASS_NAME: Comment
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 13:49
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    // 评论Id
    private Long id;
    // 昵称
    private String nickname;
    // 邮箱
    private String email;
    // 评论内容
    private String content;
    // 头像
    private String avatar;
    // 评论时间
    private Date createTime;
    // 评论和博客是多对一的关系
    private Long blogId;
    // 是否是管理员评论
    private boolean adminComment;
    // 父评论和子评论是一对多的关系
    private List<Comment> replyComments = new ArrayList<>();
    // 父评论的Id
    private Long parentCommentId;
    // 父评论
    private Comment parentComment;
    // 父评论的昵称
    private String parentNickname;
}
