package com.sys.manage.modules.sys.service;

import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;

/**
 * 角色与菜单对应关系
 */
public interface SysRoleMenuService extends BaseService<SysRoleMenuEntity> {

    /**
     *
     * 功能描述: 获取角色菜单列表，返回菜单id列表
     * @param roleId    角色id
     * @auther: tianms
     * @date: 2020/02/02 13:07
     * @return java.util.List<java.lang.String>
     */
    List<String> queryMenuIdList(String roleId);

    /**
     *
     * 功能描述: 根据列表和角色id新增
     * @param menuIdList
     * @param roleId
     * @auther: tianms
     * @date: 2020/02/07 17:09
     * @return void
     */
    void insertByList(List<String> menuIdList, String roleId);
	
}
