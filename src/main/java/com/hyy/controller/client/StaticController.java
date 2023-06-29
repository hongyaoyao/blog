package com.hyy.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: StaticController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/21 22:25
 * @Emial: 1299694047@qq.com
 */
@Controller
public class StaticController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/music")
    public String music(){
        return "music";
    }
}
