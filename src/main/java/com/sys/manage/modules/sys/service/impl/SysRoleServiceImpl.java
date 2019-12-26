package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.exception.RRException;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysRoleDao;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysRoleService;
import com.sys.manage.modules.sys.service.SysUserRoleService;
import com.sys.manage.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 角色
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void save(SysRoleEntity role) {
////		role.setCreateTime(new Date());
//
//		// 检查权限是否越权
//		checkPrems(role);
//
//		// 保存角色与菜单关系
////		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
//	}
//
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void update(SysRoleEntity role) {
//
//		// 检查权限是否越权
//		checkPrems(role);
//
//		// 更新角色与菜单关系
////		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
//	}
//
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteBatch(Long[] roleIds) {
//		// 删除角色
//
//		// 删除角色与菜单关联
//		sysRoleMenuService.deleteBatch(roleIds);
//
//		// 删除角色与用户关联
//		sysUserRoleService.deleteBatch(roleIds);
//	}
//
//	@Override
//	public List<Long> queryRoleIdList(Long createUserId) {
//		return baseDao.queryRoleIdList(createUserId);
//	}
//
//	/**
//	 * 检查权限是否越权
//	 */
//	private void checkPrems(SysRoleEntity role) {
//		// 如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
////		if (role.getCreateUserId() == Constant.SYS_CONSTANT.SUPER_ADMIN) {
////			return;
////		}
//
//		// 查询用户所拥有的菜单列表
//		List<Long> menuIdList = null; // sysUserService.queryAllMenuId(role.getCreateUserId());
//
//		// 判断是否越权
////		if (!menuIdList.containsAll(role.getMenuIdList())) {
////			throw new RRException("新增角色的权限，已超出你的权限范围");
////		}
//	}
//
//	/**
//	 * 获取所有角色列表
//	 *
//	 * @Title: queryAllRole
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @return
//	 *            设定文件
//	 * @return List<SysRoleEntity> 返回类型
//	 * @author tianms
//	 * @throws @date
//	 *             2018年6月12日 下午2:29:20
//	 */
//	@Override
//	public List<SysRoleEntity> queryAllRole() {
//		List<SysRoleEntity> roleList = baseDao.queryAllRole();
//		return roleList;
//	}
}
