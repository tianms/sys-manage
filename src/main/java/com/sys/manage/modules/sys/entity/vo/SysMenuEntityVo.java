package com.sys.manage.modules.sys.entity.vo;

import com.sys.manage.modules.sys.entity.SysMenuEntity;
import lombok.Data;

import java.util.List;

/**
 * @Author tianms
 * @Date 2019/12/23 11:12
 */
@Data
public class SysMenuEntityVo extends SysMenuEntity {

    /**
     * 子菜单列表
     */
    private List<SysMenuEntityVo> list;

}
