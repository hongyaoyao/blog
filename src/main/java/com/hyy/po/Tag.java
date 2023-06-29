package com.hyy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.po
 * @CLASS_NAME: Tag
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 14:21
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    // 标签Id
    private Long id;
    // 标签名称
    private String name;
    // 标签下的博客数量
    private Integer count;
    // 标签和博客是多对多的关系
    public List<Blog> blogs = new ArrayList<>();
}
