package com.items.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系統用戶控制器
 */
@Controller
@RequestMapping(value = "/a")
public class SysUserController {
    // 請求登入
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
