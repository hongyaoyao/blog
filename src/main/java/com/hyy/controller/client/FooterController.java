package com.hyy.controller.client;

import com.hyy.service.BlogService;
import com.hyy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: NewBlogsController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/21 22:35
 * @Emial: 1299694047@qq.com
 */
@Controller
public class FooterController {

    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private CommentService commentServiceImpl;

    @GetMapping("/footer")
    public String newblogs(Model model){
        model.addAttribute("newBlogs", blogServiceImpl.getNewBlogs(3));
        model.addAttribute("blogsCount", blogServiceImpl.getBlogsCount());
        List<Integer> views = blogServiceImpl.getBlogsViews();
        Integer allViews = 0;
        for(Integer view : views){
            allViews = allViews + view;
        }
        model.addAttribute("viewsCount", allViews);
        model.addAttribute("commentsCount", commentServiceImpl.getCommentsCount());
//        model.addAttribute("blogsCount", blogServiceImpl.getBlogsCount());
        return "_fragments :: footer";
    }
}
