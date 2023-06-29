package com.hyy.controller.client;

import com.hyy.po.Type;
import com.hyy.service.BlogService;
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
 * @PACKAGE_NAME: com.hyy.controller.client
 * @CLASS_NAME: TypeShowController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/21 15:55
 * @Emial: 1299694047@qq.com
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeServiceImpl;

    @Autowired
    private BlogService blogServiceImpl;

    @GetMapping("/types/{typeId}")
    public String types(@PathVariable("typeId") Long typeId, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model){

        List<Type> types = typeServiceImpl.getBlogsType();
        // id为-1表示是从分类导航点过来的
        if (types != null && types.size() > 0){
            if (typeId == -1){
                typeId = types.get(0).getId();
            }
        }
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", blogServiceImpl.getBlogsByTypeId(typeId, pageNum, 1));
        model.addAttribute("activeTypeId", typeId);

        return "types";
    }
}
