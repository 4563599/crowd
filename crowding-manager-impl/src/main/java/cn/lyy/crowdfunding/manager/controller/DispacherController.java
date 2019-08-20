package cn.lyy.crowdfunding.manager.controller;

import cn.lyy.crowdfunding.bean.Permission;
import cn.lyy.crowdfunding.bean.User;
import cn.lyy.crowdfunding.manager.service.UserService;
import cn.lyy.utils.AjaxResult;
import cn.lyy.utils.Const;
import cn.lyy.utils.MD5Util;
import org.activiti.engine.impl.interceptor.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    public String main(HttpSession session) {

        User user = (User) session.getAttribute(Const.LOGIN_USER);
        List<Permission> permissionList = userService.queryPermissionByUserId(user.getId());

        Map<Integer, Permission> permissionMap = new HashMap<>();

        //用于拦截许可权限
        Set<String> myUrls = new HashSet<>();

        for (Permission p : permissionList) {
            permissionMap.put(p.getId(), p);
            myUrls.add(p.getUrl());
        }
        session.setAttribute(Const.MY_URLS, myUrls);

        Permission permissionRoot = null;
        session.setAttribute("permissionRoot", permissionRoot);
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
