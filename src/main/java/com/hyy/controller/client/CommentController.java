package com.hyy.controller.client;

import com.hyy.po.Comment;
import com.hyy.po.User;
import com.hyy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: CommentController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/20 16:48
 * @Emial: 1299694047@qq.com
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentServiceImpl;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable("blogId") Long blogId, Model model){
        model.addAttribute("comments", commentServiceImpl.getCommentsByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        // 看看是否管理员评论
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }
        commentServiceImpl.saveComment(comment);
        return "redirect:/comments/" + comment.getBlogId();
    }
}
