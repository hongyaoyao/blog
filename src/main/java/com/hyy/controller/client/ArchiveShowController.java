package com.hyy.controller.client;

import com.hyy.po.Blog;
import com.hyy.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: ArchiveShowController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/21 21:33
 * @Emial: 1299694047@qq.com
 */
@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/archives")
    public String archives(Model model) {
        int blogCount = 0;
        Map<String, List<Blog>> archiveMap = blogServiceImpl.archiveBlogs();
        for(List<Blog> list : archiveMap.values()){
            blogCount += list.size();
        }
        model.addAttribute("archiveMap", archiveMap);
        model.addAttribute("blogCount", blogCount);
        return "archives";
    }

}
