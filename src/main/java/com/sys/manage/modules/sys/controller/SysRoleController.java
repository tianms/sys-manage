package com.sys.manage.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;

	/**
	 * 角色列表
	 */
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		// 如果不是超级管理员，则只查询自己创建的角色列表
//		if (getUserId() != Constant.SYS_CONSTANT.SUPER_ADMIN) {
//			params.put("createUserId", getUserId());
//		}

		return R.ok().put("page", null);
	}

	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	public R select() {
		Map<String, Object> map = new HashMap<>();

		// 如果不是超级管理员，则只查询自己所拥有的角色列表
//		if (getUserId() != Constant.SYS_CONSTANT.SUPER_ADMIN) {
//			map.put("createUserId", getUserId());
//		}

		return R.ok().put("list", null);
	}

	/**
	 * 角色信息
	 */
	@GetMapping("/info/{roleId}")
	public R info(@PathVariable("roleId") Long roleId) {
		SysRoleEntity role = null; // sysRoleService.selectById(roleId);

		// 查询角色对应的菜单
//		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
//		role.setMenuIdList(menuIdList);

		return R.ok().put("role", role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	public R save(@RequestBody SysRoleEntity role) {
//		sysRoleService.save(role);
		return R.ok();
	}

	/**
	 * 修改角色
	 */
	@SysLog("修改角色")
	@PostMapping("/update")
	public R update(@RequestBody SysRoleEntity role) {
		sysRoleService.update(role);
		return R.ok();
	}

	/**
	 * 删除角色
	 */
	@SysLog("删除角色")
	@PostMapping("/delete")
	public R delete(@RequestBody Long[] roleIds) {
//		sysRoleService.deleteBatch(roleIds);

		return R.ok();
	}
}
