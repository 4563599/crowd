package cn.lyy.crowdfunding.manager.controller;

import cn.lyy.crowdfunding.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test(String name) {
        testService.insert();
        System.out.println("/test");
        return "success";
    }

}