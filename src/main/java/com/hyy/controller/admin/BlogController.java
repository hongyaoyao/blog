package com.hyy.controller.admin;

import com.hyy.po.Blog;
import com.hyy.po.User;
import com.hyy.service.BlogService;
import com.hyy.service.TagService;
import com.hyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.admin
 * @CLASS_NAME: BlogController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 16:42
 * @Emial: 1299694047@qq.com
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogServiceImpl;

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private TagService tagServiceImpl;

    // 管理端博客管理页面首页
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        // 用于管理端博客首页的查询功能，其中需要选择分类
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("pageInfo", blogServiceImpl.getAllBlogs(pageNum, 5));
        return "admin/blogs";
    }

    // 管理端博客管理页面首页搜索
    @PostMapping("/blogs/search")
    public String search(Blog blog, @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum, Model model){
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("pageInfo", blogServiceImpl.searchAllBlogs(blog, pageNum, 5));
        return "admin/blogs :: blogList";
    }



    // 跳转到博客新增页面
    @GetMapping("/blogs/input")
    public String inputBlog(Model model) {
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("tags", tagServiceImpl.getAllTag());
        // 由于新增页面和修改页面是同一个，所以为了在新增页面不出错，需要给前端返回一个空的Blog对象
        model.addAttribute("blog", new Blog());
        return "admin/blogs_input";
    }

    // 跳转到博客编辑修改页面
    @GetMapping("/blogs/{id}/input")
    public String editBlog(@PathVariable Long id, Model model){
        model.addAttribute("types", typeServiceImpl.getAllType());
        model.addAttribute("tags", tagServiceImpl.getAllTag());
        // 由于新增页面和修改页面是同一个，所以为了在新增页面不出错，需要给前端返回一个空的Blog对象
        model.addAttribute("blog", blogServiceImpl.getBlogById(id));
        return "admin/blogs_input";
    }

    // 新增
    @PostMapping("/blogs")
    public String savaPost(Blog blog, RedirectAttributes attributes, HttpSession session){
        //设置user属性
        blog.setUser((User)session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        int res = blogServiceImpl.saveBlogAndTags(blog);
        if(res == 1){
            attributes.addFlashAttribute("message", "新增博客成功");
        }else{
            attributes.addFlashAttribute("message", "新增博客失败");
        }
        return "redirect:/admin/blogs";
    }


    // 修改
    @PostMapping("/blogs/{id}")
    public String editPost(Blog blog, RedirectAttributes attributes){
        int res = blogServiceImpl.updateBlog(blog);
        if(res == 1){
            attributes.addFlashAttribute("message", "修改博客成功");
        }else{
            attributes.addFlashAttribute("message", "修改博客失败");
        }
        return "redirect:/admin/blogs";
    }

    // 删除
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes) {
        blogServiceImpl.deleteBlog(id);
        attributes.addFlashAttribute("message", "博客删除成功");
        return "redirect:/admin/blogs";
    }
}
