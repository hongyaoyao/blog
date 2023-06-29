package com.hyy.service.impl;

import com.hyy.mapper.TagMapper;
import com.hyy.po.Tag;
import com.hyy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.service.impl
 * @CLASS_NAME: TagServiceImpl
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 22:31
 * @Emial: 1299694047@qq.com
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int saveTag(Tag tag) {
        return tagMapper.saveTag(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagMapper.getTag(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getBlogsTag() {
        return tagMapper.getBlogsTag();
    }
}
