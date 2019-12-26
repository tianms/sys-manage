package com.sys.manage.modules.sys.entity.vo;

import com.sys.manage.modules.sys.entity.SysUserEntity;
import lombok.Data;

/**
 * @Auther: tianms
 * @Date: 2019/12/10 22:24
 * @Description:
 */
@Data
public class SysUserEntityVo extends SysUserEntity {

    /**
     * 角色id
     */
    private String roleId;
}
