package com.study.blog.controller.admin;

import com.study.blog.pojo.Type;
import com.study.blog.service.TypeService;
import com.study.blog.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping("/types/{page}")
    public String list(@PathVariable("page") Integer page,Model model){
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
        int count = typeService.count();
        pages.setTotalCount(count);
        List<Type> list = typeService.getTypePage(pages.getCurrentPage(), pages.getPageSize());
        model.addAttribute("pages",pages);
        model.addAttribute("list",list);
        return "admin/types";
    }
    @GetMapping("/types/input")
    public String input(){
        return "admin/types-input";
    }
    @PostMapping("/types/input")
    public String addType(@RequestParam String name,RedirectAttributes redirectAttributes){
        typeService.addType(name);
        redirectAttributes.addFlashAttribute("message","插入成功");
        return "redirect:/admin/types/0";
    }
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        typeService.deleteType(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types/0";
    }
}
