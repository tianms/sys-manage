package com.sys.manage.modules.sys.controller;

import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import com.sys.manage.modules.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends AbstractController {
	@Autowired
	private SysMenuService sysMenuService;

	/**
	 *
	 * 获取登录用户的菜单列表和权限列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/19 21:05
	 * @param
	 * @return com.sys.manage.common.utils.R
	*/
	@RequestMapping(value = "/nav")
	public R nav () {
	    // 获取角色的菜单列表
		List<SysMenuEntityVo> sysMenuVoList = sysMenuService.queryRoleMenuList(getRoleId());
		// 获取角色的权限
		List<String> perMissionList = sysMenuService.queryPerMissionsByRoleId(getRoleId());
		return R.ok().put("menuList", sysMenuVoList).put("permissions", perMissionList);
	}

	/**
	 *
	 * 所有菜单列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/16 22:24
	 * @param  params
	 * @return java.util.List<com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo>
	*/
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<SysMenuEntityVo> list(@RequestBody Map<String, Object> params) {
		// 菜单列表
		List<SysMenuEntityVo> menuList = sysMenuService.queryList(params);
		// 循环获取上级菜单的信息
		for (SysMenuEntityVo sysMenuEntityVo : menuList) {
			// 根据父菜单id获取父菜单信息
			SysMenuEntity parentMenuEntity = sysMenuService.queryById(sysMenuEntityVo.getParentId());
			if (parentMenuEntity != null) {
				sysMenuEntityVo.setParentMenuName(parentMenuEntity.getName());
			}
		}

		return menuList;
	}

	/**
	 *
	 * 获取不包括按钮菜单列表（主要用于菜单编辑选择上级菜单使用）
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/17 22:00
	 * @param
	 * @return com.sys.manage.common.utils.R
	*/
	@RequestMapping(value = "/noButtonMenuList")
	public R noButtonMenuList() {
		// 查询包含按钮的菜单列表
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

		// 添加顶级菜单，以便选择使用
		SysMenuEntity upMenu = new SysMenuEntity();
		upMenu.setMenuId(Constant.MENU.ROOTMENUID);
		upMenu.setName(Constant.MENU.ROOTMENUNAME);
		upMenu.setParentId(Constant.MENU.ROOTMENUPARENTID);
		menuList.add(upMenu);

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 新增菜单信息
	 * 功能描述:
	 * @param sysMenuEntityVo
	 * @auther: tianms、
	 * @date: 2020/01/23 13:27
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/save")
	public R save(@RequestBody SysMenuEntityVo sysMenuEntityVo) {

		sysMenuService.insert(sysMenuEntityVo);

		return R.ok();
	}

	/**
	 * 修改菜单信息
	 * 功能描述:
	 * @param sysMenuEntityVo
	 * @auther: tianms
	 * @date: 2020/01/23 13:28
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/update")
	public R update(@RequestBody SysMenuEntityVo sysMenuEntityVo) {

		sysMenuService.update(sysMenuEntityVo);

		return R.ok();
	}

	/**
	 * 获取菜单信息详细信息
	 * 功能描述:
	 * @param param
	 * @auther: tianms
	 * @date: 2020/01/28 15:16
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/info")
	public R info(@RequestBody Map<String, Object> param) {
		SysMenuEntityVo sysMenuEntityVo = sysMenuService.queryById(String.valueOf(param.get("id")));
		return R.ok().put("menu", sysMenuEntityVo);
	}

	/**
	 *
	 * 批量是删除菜单
	 *
	 * 功能描述:
	 * @param ids
	 * @auther: tianms
	 * @date: 2020/01/29 16:37
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/delete")
	public R delete(@RequestBody List<String> ids) {

		sysMenuService.deleteBatch(ids);

		return R.ok();
	}


}
