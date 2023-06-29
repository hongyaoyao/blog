package com.hyy.controller.admin;

import com.hyy.po.User;
import com.hyy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.admin
 * @CLASS_NAME: LoginController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 15:15
 * @Emial: 1299694047@qq.com
 */
@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userServiceImpl;

    // 跳转到登陆页面
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    // 进行登陆验证
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userServiceImpl.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    //注销
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //清空session里的user对象
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
