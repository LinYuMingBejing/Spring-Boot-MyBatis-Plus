package com.items.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.api.entity.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu>{
    /**
     * 根據用戶名獲取對應的菜單
     */
    List<SysMenu> findByUsername(String username);
}
