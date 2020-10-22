package com.items.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
public class SysUser{
    @TableId(type= IdType.AUTO)// 主鍵自動增長
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    private Integer status;
    private String userType;

    private Integer createBy;
    private Date createDate;
    private Integer updateBy;
    private Date updateDate;

    @TableLogic // 用於設置該字段為邏輯字段
    private String delFlag;


    // mybatis plus 忽略該屬性和數據庫的映射
    @TableField(exist = false)
    private List<SysRole> roles;
}
