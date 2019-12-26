package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.MenuUtils;
import com.sys.manage.config.EhcacheService;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysMenuDao;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import com.sys.manage.modules.sys.service.SysMenuService;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private EhcacheService ehcacheService;

	/**
	 *
	 * 获取角色菜单列表
	 *
	 * @Description: 根据传入的角色id获取角色所拥有的的菜单列表
	 *
	 * @author tianms
	 * @date 2019/12/15 16:31
	 * @param  roleId
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
	 */
	@Override
	public List<SysMenuEntityVo> getRoleMenuList(String roleId) {
		// Step:1 根据角色查询缓存中角色绑定的菜单列表
		Object object = ehcacheService.get(roleId);
		// Step:2 如果缓存中未查询到角色绑定的列表，则从数据库中查询角色绑定的列表信息，并返回
		if (object == null) {
			// 根据角色获取角色列表
			List<SysMenuEntityVo> sysMenuList = this.queryListByRoleId(roleId);
			// 存入缓存
			ehcacheService.putWithTime(roleId, sysMenuList, Constant.SYS_CONSTANT.TOKEN_EXPIRE);
			return sysMenuList;
		} else {
			return (List<SysMenuEntityVo>) object;
		}
	}

	/**
	 *
	 * 根据角色id获取菜单列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/19 21:13
	 * @param  roleId
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
	 */
	@Override
	public List<SysMenuEntityVo> queryListByRoleId (String roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<SysMenuEntity> sysMenuList = baseDao.queryList(params);
		// 格式化菜单为页面需要的菜单列表类型
		List<SysMenuEntityVo> sysMenuEntityVo = MenuUtils.formatMenuList(sysMenuList, "");
		return sysMenuEntityVo;
	}



	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if (menuIdList == null) {
			return menuList;
		}

		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for (SysMenuEntity menu : menuList) {
			if (menuIdList.contains(menu.getMenuId())) {
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId) {
		return baseDao.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseDao.queryNotButtonList();
	}


	@Override
	public void delete(Long menuId) {
		// 删除菜单
		// 删除菜单与角色关联
	}

	public List<String> queryRoleId




	/**
	 * 查询包含列表的菜单
	 * <p>
	 * Title: queryContainListMenu
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @return
	 * @see SysMenuService#queryContainListMenu()
	 * @author tianms
	 * @date 2018年6月11日 下午3:49:14
	 */
	@Override
	public List<SysMenuEntity> queryContainListMenu() {
		List<SysMenuEntity> meunList = baseDao.queryContainListMenu();
		return meunList;
	}
}
