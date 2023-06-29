package com.hyy.service;

import com.hyy.po.Tag;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service
 * @CLASS_NAME: TagService
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 22:29
 * @Emial: 1299694047@qq.com
 */
public interface TagService {

    int saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTag();

    int updateTag(Tag tag);

    int deleteTag(Long id);

    List<Tag> getBlogsTag();
}
