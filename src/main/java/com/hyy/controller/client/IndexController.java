package com.hyy.controller.client;

import com.hyy.po.Blog;
import com.hyy.po.Tag;
import com.hyy.po.Type;
import com.hyy.service.BlogService;
import com.hyy.service.CommentService;
import com.hyy.service.TagService;
import com.hyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller
 * @CLASS_NAME: IndexController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/11 16:09
 * @Emial: 1299694047@qq.com
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private TagService tagServiceImpl;

    @Autowired
    private CommentService commentServiceImpl;

    // 首页获取博客列表
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {

        List<Type> types = typeServiceImpl.getBlogsType();
        List<Tag> tags = tagServiceImpl.getBlogsTag();
        List<Blog> hostBlogs = blogServiceImpl.getHostBlogs(5);
        List<Blog> recommendBlogs = blogServiceImpl.getAllRecommendBlogs(5);

        model.addAttribute("pageInfo", blogServiceImpl.getIndexBlogs(pageNum,1));
        model.addAttribute("types", types);
        model.addAttribute("tags", tags);
        model.addAttribute("hostBlogs", hostBlogs);
        model.addAttribute("recommendBlogs", recommendBlogs);
        return "index";
    }

    //搜索
    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam("query") String query, Model model) {

        model.addAttribute("pageInfo", blogServiceImpl.getSearchBlogs(query, pageNum, 1));
        model.addAttribute("query", query);
        return "search";
    }

    // 博客详情
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogServiceImpl.getDetailedBlog(id));
        model.addAttribute("comments", commentServiceImpl.getCommentsByBlogId(id));
        return "blog";
    }
}
