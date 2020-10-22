package com.items.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.items.api.entity.SysMenu;
import com.items.api.mapper.SysMenuMapper;
import com.items.api.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISystemMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findListByName(String username){
        return sysMenuMapper.findByUsername(username);
    }
}
