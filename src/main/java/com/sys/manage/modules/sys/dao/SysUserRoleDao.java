package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID
	 */
	Long queryRoleId(Long userId);


	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
