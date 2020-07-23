package com.items.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;

    @TableField(fill = FieldFill.INSERT)
    private Date createdTime; // 駝峰式命名 created_time

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version; // 版本號

    @TableLogic
    private Integer deleted;
}
