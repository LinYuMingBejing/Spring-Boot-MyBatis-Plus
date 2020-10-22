package com.items.api.controller;

import com.items.api.entity.SysMenu;
import com.items.api.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/a")
public class SysIndexController {

    @Autowired
    private ISystemMenuService systemMenuService;

    @RequestMapping("/index")
    public String index(Model model){
        // 讀取當前登入成功用戶的角色，並根據角色加載菜單
        // 獲取 spring security 用戶
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        // 讀取當前用戶管理菜單
        List<SysMenu> menuList = systemMenuService.findListByName(userDetails.getUsername());
        model.addAttribute("menuList", menuList);
        return "index";
    }
}
