package com.sys.manage.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户与角色对应关系
 */
@Data
public class SysUserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;


}
