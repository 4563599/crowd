package cn.lyy.crowdfunding.manager.controller;

import cn.lyy.crowdfunding.bean.User;
import cn.lyy.crowdfunding.manager.service.UserService;
import cn.lyy.utils.AjaxResult;
import cn.lyy.utils.Const;
import cn.lyy.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DispacherController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }


    //异步请求
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct, String userpswd, String type, HttpSession httpSession) {

        AjaxResult result = new AjaxResult();

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("loginacct", loginacct);
            paramMap.put("userpswd", MD5Util.digest(userpswd));
            paramMap.put("type", type);
            User user = userService.login(paramMap);
            httpSession.setAttribute(Const.LOGIN_USER, user);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("登录失败！");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/index.htm";
    }

    //同步请求
//    @RequestMapping("/doLogin")
//    public String doLogin(String loginacct, String userpswd, String type, HttpSession httpSession) {
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("loginacct", loginacct);
//        paramMap.put("userpswd", userpswd);
//        paramMap.put("type", type);
//        User user = userService.login(paramMap);
//        httpSession.setAttribute(Const.LOGIN_USER, user);
//        return "redirect:/main.htm";
//    }
}
