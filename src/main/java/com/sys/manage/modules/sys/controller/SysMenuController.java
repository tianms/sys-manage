package com.sys.manage.modules.sys.controller;

import java.util.List;
import java.util.Set;

import com.sys.manage.common.exception.RRException;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import com.sys.manage.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.sys.entity.SysMenuEntity;

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
	 * 获取登录用户的菜单列表
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
		List<SysMenuEntityVo> sysMenuVoList = sysMenuService.getRoleMenuList(getRoleId());
		// 获取角色的权限
		List<String> perMissionList = sysMenuService.queryRoleIdPerMissions(getRoleId());
		return R.ok().put("menuList", sysMenuVoList).put("permissions", perMissionList);
	}

	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
	public List<SysMenuEntity> list() {
//		List<SysMenuEntity> menuList = sysMenuService.selectList(null);
//		for (SysMenuEntity sysMenuEntity : menuList) {
//			SysMenuEntity parentMenuEntity = sysMenuService.selectById(sysMenuEntity.getParentId());
//			if (parentMenuEntity != null) {
//				sysMenuEntity.setParentName(parentMenuEntity.getName());
//			}
//		}

		return null;
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
	public R select() {
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
//		root.setMenuId(0L);
//		root.setName("一级菜单");
//		root.setParentId(-1L);
//		root.setOpen(true);
		menuList.add(root);

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
	public R info(@PathVariable("menuId") Long menuId) {
		SysMenuEntity menu = null; // sysMenuService.selectById(menuId);
		return R.ok().put("menu", menu);
	}

	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@PostMapping("/save")
	public R save(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);

//		sysMenuService.insert(menu);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@PostMapping("/update")
	public R update(@RequestBody SysMenuEntity menu) {
		// 数据校验
		verifyForm(menu);

//		sysMenuService.updateById(menu);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@PostMapping("/delete/{menuId}")
	public R delete(@PathVariable("menuId") long menuId) {
		if (menuId <= 31) {
			return R.error("系统菜单，不能删除");
		}

		// 判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if (menuList.size() > 0) {
			return R.error("请先删除子菜单或按钮");
		}

		sysMenuService.delete(menuId);

		return R.ok();
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new RRException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new RRException("上级菜单不能为空");
		}
	}

}
