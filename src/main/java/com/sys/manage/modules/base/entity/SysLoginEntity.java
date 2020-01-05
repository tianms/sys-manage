package com.sys.manage.modules.base.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录表单
 */
@Data
public class SysLoginEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 新密码,修改密码时使用
     */
    private String newPassWord;
}
