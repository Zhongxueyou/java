package com.study.blog.controller.admin;

import com.study.blog.pojo.User;
import com.study.blog.service.UserService;
import com.study.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes){
        User user = userService.findUser(username, MD5Utils.code(password));
        if(user!=null){
            httpSession.setAttribute("user",user);
            System.out.println(user);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";

    }
}
