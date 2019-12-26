package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysUserRoleDao;
import com.sys.manage.modules.sys.entity.SysUserRoleEntity;
import com.sys.manage.modules.sys.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

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
	@Override
	public SysUserRoleEntity queryByUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		SysUserRoleEntity sysUserRoleEntity = baseDao.queryByMap(params);
		return sysUserRoleEntity;
	}

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
	@Override
	public List<SysUserRoleEntity> queryByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<SysUserRoleEntity> sysUserRoleEntities = baseDao.queryList(params);
		return sysUserRoleEntities;
	}



	@Override
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		//先删除用户与角色关系

		if(roleIdList == null || roleIdList.size() == 0){
			return ;
		}

		//保存用户与角色关系
		List<SysUserRoleEntity> list = new ArrayList<>(roleIdList.size());
		for(String roleId : roleIdList){
			SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
			sysUserRoleEntity.setUserId(userId);
			sysUserRoleEntity.setRoleId(roleId);

			list.add(sysUserRoleEntity);
		}
	}

	@Override
	public Long queryRoleId(Long userId) {
		return baseDao.queryRoleId(userId);
	}

	@Override
	public int deleteBatch(Long[] roleIds){
		return baseDao.deleteBatch(roleIds);
	}
}
