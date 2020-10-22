package com.items.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.items.api.entity.SysMenu;

import java.util.List;


public interface ISystemMenuService extends IService<SysMenu> {
    /*
    * 根據用戶名獲取對應菜單
    *
    */
    List<SysMenu> findListByName(String username);
}
