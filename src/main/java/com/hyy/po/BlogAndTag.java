package com.hyy.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.po
 * @CLASS_NAME: BlogAndTag
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 14:26
 * @Emial: 1299694047@qq.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogAndTag {

    // 标签Id
    private Long tagId;
    // 博客Id
    private Long blogId;
}
