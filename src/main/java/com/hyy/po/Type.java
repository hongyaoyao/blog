package com.hyy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.po
 * @CLASS_NAME: Type
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 13:48
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    // 分类Id
    private Long id;
    // 分类名称
    private String name;
    // 分类下的博客数量
    private Integer count;
    // 分类和博客是一对多的关系
    private List<Blog> blogs = new ArrayList<>();
}