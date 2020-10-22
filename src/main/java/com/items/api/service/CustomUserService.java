package com.items.api.service;

import com.items.api.entity.SysRole;
import com.items.api.entity.SysUser;
import com.items.api.mapper.SysUserMapper;
import com.items.api.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        SysUser user = sysUserMapper.findUserByName(username);
        if (user != null) {
            List<SysRole> roles = user.getRoles(); // 獲取用戶角色
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (SysRole role : roles){
                if (role != null && role.getName()!=null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("admin" +username+ "do not found" );
        }

    }
}
