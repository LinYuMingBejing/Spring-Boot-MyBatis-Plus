package com.items.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.items.api.entity.SysUser;
import org.springframework.stereotype.Component;

@Component
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根據用戶名查詢用戶
     */
    SysUser findUserByName(String username);
}
