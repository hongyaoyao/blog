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
 * @CLASS_NAME: Blog
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/12 21:11
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    // 博客Id
    private Long id;
    // 博客标题
    private String title;
    // 博客内容
    private String content;
    // 首图地址
    private String firstPicture;
    // 标记是否原创
    private String flag;
    // 浏览次数
    private Integer views;
    // 是否开启赞赏
    private boolean appreciation;
    // 是否开启版权说明
    private boolean shareStatement;
    // 是否开启评论
    private boolean commentabled;
    // 是否发布
    private boolean published;
    // 是否推荐
    private boolean recommend;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    // 博客描述
    private String description;
    // 博客和分类是多对一的关系
    private Type type;
    // 博客和用户是多对一的关系
    private User user;
    // 分类Id
    private Long typeId;
    // 用户Id
    private Long userId;
    // 本博客的所有标签Id，使用英文逗号隔开
    private String tagIds;
    // 博客和评论是一对多的关系
    private List<Comment> comments = new ArrayList<>();
    // 博客和标签是多对多的关系
    private List<Tag> tags = new ArrayList<>();
}
