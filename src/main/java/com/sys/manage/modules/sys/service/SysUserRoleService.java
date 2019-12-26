package com.sys.manage.modules.sys.service;

import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 */
public interface SysUserRoleService extends BaseService<SysUserRoleEntity> {

	/**
	 *
	 * 根据用户id查询角色
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/19 20:46
	 * @param  userId
	 * @return com.sys.manage.modules.sys.entity.SysUserRoleEntity
	*/
	SysUserRoleEntity queryByUserId(String userId);

	/**
	 *
	 * 根据角色id获取列表信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/19 20:47
	 * @param  roleId
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysUserRoleEntity>
	*/
	List<SysUserRoleEntity> queryByRoleId(String roleId);





	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * 根据用户ID，获取角色ID
	 */
	Long queryRoleId(Long userId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
}
