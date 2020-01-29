package com.sys.manage.modules.sys.service;

import com.sys.manage.common.utils.R;
import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;

import java.util.List;

/**
 * 菜单管理
 */
public interface SysMenuService extends BaseService<SysMenuEntityVo> {

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
    List<SysMenuEntityVo> queryMenuListByRoleId (String roleId);

	/**
	 *
	 * 获取用户菜单列表
	 *
	 * @Description: 根据传入的角色id获取角色所拥有的的菜单列表
	 *
	 * @author tianms
	 * @date 2019/12/15 16:31
	 * @param  roleId
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
	*/
	List<SysMenuEntityVo> queryRoleMenuList(String roleId);


    /**
     * 根据角色id获取格式化后的菜单列表
     * 功能描述:
     * @param roleId
     * @auther: tianms
     * @date: 2019/12/26 09:36
     * @return java.util.List<com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo>
     */
    List<SysMenuEntityVo> queryFormatMenuListByRoleId (String roleId);


	/**
	 * 获取角色的权限
	 * 功能描述:
	 * @param roleId    角色id
	 * @auther: tianms
	 * @date: 2019/12/26 09:27
	 * @return java.util.List<java.lang.String>
	 */
    List<String> queryPerMissionsByRoleId (String roleId);

	/**
	 *
	 * 获取不包括按钮菜单列表（主要用于菜单编辑选择上级菜单使用）
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/18 22:01
	 * @param
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
	*/
	List<SysMenuEntity> queryNotButtonList();

}
