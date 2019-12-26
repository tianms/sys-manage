package com.sys.manage.modules.sys.service;

import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;

import java.util.List;

/**
 * 菜单管理
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

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
    List<SysMenuEntity> queryMenuListByRoleId (String roleId);

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
	List<SysMenuEntityVo> getRoleMenuList(String roleId);


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
    List<String> queryRoleIdPerMissions (String roleId);


	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param parentId
	 *            父菜单ID
	 * @param menuIdList
	 *            用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 *
	 * @param parentId
	 *            父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();


	/**
	 * 删除
	 */
	void delete(Long menuId);

	/**
	 * 查询包含列表的菜单
	 *
	 * @Title: queryContainListMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 *            设定文件
	 * @return List<SysMenuEntity> 返回类型
	 * @author tianms
	 * @throws @date
	 *             2018年6月11日 下午3:47:32
	 */
	List<SysMenuEntity> queryContainListMenu();
}
