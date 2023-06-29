package com.hyy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyy.po.Type;
import com.hyy.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.admin
 * @CLASS_NAME: TypeController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 17:41
 * @Emial: 1299694047@qq.com
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeServiceImpl;

    // 管理端分类页面首页
    @GetMapping("/types")
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<Type> allType = typeServiceImpl.getAllType();
        //封装分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allType);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    // 跳转到分类新增页面
    @GetMapping("/types/input")
    public String inputType(Model model) {
        // 由于新增页面和修改页面是同一个，所以为了在新增页面不出错，需要给前端返回一个空的Type对象
        model.addAttribute("type", new Type());
        return "admin/types_input";
    }

    // 跳转到分类编辑页面
    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable Long id, Model model) {
        Type type = typeServiceImpl.getType(id);
        model.addAttribute("type", type);
        return "admin/types_input";
    }

    // 新增
    @PostMapping("/types")
    public String savePost(Type type, RedirectAttributes attributes) {
        Type t = typeServiceImpl.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "分类名称重复，请换一个！");
        } else {
            typeServiceImpl.saveType(type);
            attributes.addFlashAttribute("message", "分类名称添加成功！");
        }
        return "redirect:/admin/types";
    }

    // 修改
    @PostMapping("/types/{id}")
    public String editPost(Type type, RedirectAttributes attributes) {
        Type t = typeServiceImpl.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "分类名称重复，请换一个！");
        } else {
            typeServiceImpl.updateType(type);
            attributes.addFlashAttribute("message", "分类名称修改成功！");
        }
        return "redirect:/admin/types";
    }

    // 删除
    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable Long id, RedirectAttributes attributes) {
        typeServiceImpl.deleteType(id);
        attributes.addFlashAttribute("message", "分类名称删除成功");
        return "redirect:/admin/types";
    }


}
