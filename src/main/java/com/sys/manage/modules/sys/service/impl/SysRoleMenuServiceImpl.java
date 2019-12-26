package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysRoleMenuDao;
import com.sys.manage.modules.sys.entity.SysRoleMenuEntity;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void saveOrUpdate(String roleId, List<String> menuIdList) {
//		//先删除角色与菜单关系
////		deleteBatch(new String[]{roleId});
//
//		if(menuIdList.size() == 0){
//			return ;
//		}
//
//		//保存角色与菜单关系
//		List<SysRoleMenuEntity> list = new ArrayList<>(menuIdList.size());
//		for(String menuId : menuIdList){
//			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
//			sysRoleMenuEntity.setMenuId(menuId);
//			sysRoleMenuEntity.setRoleId(roleId);
//
//			list.add(sysRoleMenuEntity);
//		}
////		this.insertBatch(list);
//	}
//
//	@Override
//	public List<Long> queryMenuIdList(Long roleId) {
//		return baseDao.queryMenuIdList(roleId);
//	}
//
//	@Override
//	public int deleteBatch(Long[] roleIds){
//		return baseDao.deleteBatch(roleIds);
//	}

}
