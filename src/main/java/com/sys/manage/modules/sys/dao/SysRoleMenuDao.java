package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysRoleMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色与菜单对应关系
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {

    /**
     *
     * 功能描述: 获取角色菜单列表，返回菜单id列表
     * @param roleId
     * @auther: tianms
     * @date: 2020/02/02 13:05
     * @return java.util.List<java.lang.String>
     */
    List<String> queryMenuIdList(@Param("roleId") String roleId);
}
