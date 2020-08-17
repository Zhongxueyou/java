package com.study.blog.controller.admin;

import com.study.blog.pojo.Tag;
import com.study.blog.pojo.Type;
import com.study.blog.service.TagService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    @GetMapping("/tags/{page}")
    public String list(@PathVariable("page") Integer page, Model model){
        //保存当前页
        int currentPage=0;
        //接受当前页
        if(page==0){
            currentPage=1;
        }else {
            currentPage=page;
        }
        Pages pages=new Pages();
        pages.setCurrentPage(currentPage);
        int count = tagService.count();
        pages.setTotalCount(count);
        List<Tag> listTag = tagService.getTagPage(pages.getCurrentPage(), pages.getPageSize());
        model.addAttribute("pages",pages);
        model.addAttribute("listTag",listTag);
        return "admin/tags";
    }
    @GetMapping("/tags/input")
    public String input(){
        return "admin/tags-input";
    }
    @PostMapping("/tags/input")
    public String addTag(@RequestParam String name, RedirectAttributes redirectAttributes){
        tagService.addTag(name);
        redirectAttributes.addFlashAttribute("message","插入成功");
        return "redirect:/admin/tags/0";
    }
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags/0";
    }
}
