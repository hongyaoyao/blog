package com.hyy.controller.client;

import com.hyy.po.Tag;
import com.hyy.service.BlogService;
import com.hyy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: TagShowController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/21 17:06
 * @Emial: 1299694047@qq.com
 */
@Controller
public class TagShowController {

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;


    @GetMapping("/tags/{tagId}")
    public String types(@PathVariable("tagId") Long tagId, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model) {

        List<Tag> tags = tagServiceImpl.getBlogsTag();
        // id为-1表示是从标签导航点过来的
        if (tags != null && tags.size() > 0) {
            if (tagId == -1) {
                tagId = tags.get(0).getId();
            }
        }
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", blogServiceImpl.getBlogsByTagId(tagId, pageNum, 1));
        model.addAttribute("activeTagId", tagId);

        return "tags";
    }

}
