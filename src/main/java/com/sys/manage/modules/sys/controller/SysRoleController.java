package com.sys.manage.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	 *
	 * 角色列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/01 20:32
	 * @param  params
	 * @return com.sys.manage.common.utils.R
	*/
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public R list(@RequestParam Map<String, Object> params) {

		List<SysRoleEntity> sysRoleList = sysRoleService.queryList(params);

		return R.ok().put("list", sysRoleList);
	}

	/**
	 *
	 * 分页角色列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/01 20:32
	 * @param  params
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public R pageList(@RequestParam Map<String, Object> params) {

		PageInfo pageInfo = sysRoleService.queryPage(params);

		return R.ok().put("page", pageInfo);
	}


	/**
	 *
	 * 根据角色id获取角色信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/01 20:50
	 * @param  id
	 * @return com.sys.manage.common.utils.R
	*/
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public R info(@RequestBody String id) {

		SysRoleEntity role = sysRoleService.queryById(id);

		return R.ok().put("role", role);
	}

	/**
	 * 保存角色
	 */
	@SysLog("保存角色")
	@PostMapping("/save")
	public R save(@RequestBody SysRoleEntity role) {
		sysRoleService.insert(role);
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
