package com.hyy.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hyy.po.Tag;
import com.hyy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy.controller.admin
 * @CLASS_NAME: TagController
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/13 22:34
 * @Emial: 1299694047@qq.com
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagServiceImpl;

    // 管理端标签页面首页
    @GetMapping("/tags")
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model) {
        PageHelper.startPage(pageNum, 5);
        List<Tag> allTag = tagServiceImpl.getAllTag();
        //得到分页结果对象
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    // 跳转到标签新增页面
    @GetMapping("/tags/input")
    public String inputTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags_input";
    }

    // 跳转到标签编辑页面
    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagServiceImpl.getTag(id));
        return "admin/tags_input";
    }

    // 新增
    @PostMapping("/tags")
    public String savePost(Tag tag, RedirectAttributes attributes) {
        Tag t = tagServiceImpl.getTagByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "标签名称重复，请换一个！");
        } else {
            tagServiceImpl.saveTag(tag);
            attributes.addFlashAttribute("message", "标签名称添加成功！");
        }
        return "redirect:/admin/tags";
    }

    // 修改
    @PostMapping("/tags/{id}")
    public String editPost(@PathVariable Long id, Tag tag, RedirectAttributes attributes) {
        Tag t = tagServiceImpl.getTagByName(tag.getName());
        if (t != null) {
            attributes.addFlashAttribute("message", "标签名称重复，请换一个！");
        } else {
            tagServiceImpl.updateTag(tag);
            attributes.addFlashAttribute("message", "标签名称修改成功！");
        }
        return "redirect:/admin/tags";
    }

    // 删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        tagServiceImpl.deleteTag(id);
        attributes.addFlashAttribute("message", "标签名称删除成功");
        return "redirect:/admin/tags";
    }


}
