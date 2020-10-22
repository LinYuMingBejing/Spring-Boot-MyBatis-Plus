package com.items.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class SysMenu {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer pid;
    private String name;
    private String url;
    private String perms;
    private String icon;
    private Integer sort;

    @TableLogic // 用於設置該字段為邏輯字段
    private String delFlag;
}
