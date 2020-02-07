package com.sys.manage.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.entity.vo.SysRoleEntityVo;
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

	/**
	 *
	 * 功能描述: 角色列表
	 * @param params
	 * @auther: tianms
	 * @date: 2020/01/31 16:36
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public R list(@RequestParam Map<String, Object> params) {

		List<SysRoleEntityVo> sysRoleList = sysRoleService.queryList(params);

		return R.ok().put("list", sysRoleList);
	}

	/**
	 *
	 * 功能描述: 分页角色列表
	 * @param params
	 * @auther: tianms
	 * @date: 2020/01/31 16:36
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	public R pageList(@RequestParam Map<String, Object> params) {

		PageInfo pageInfo = sysRoleService.queryPage(params);

		return R.ok().put("page", pageInfo);
	}

	/**
	 *
	 * 功能描述: 根据角色id获取角色信息
	 * @param params
	 * @auther: tianms
	 * @date: 2020/01/31 16:35
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public R info(@RequestBody Map<String, Object> params) {

		SysRoleEntityVo sysRoleEntityVo = sysRoleService.queryById(String.valueOf(params.get("id")));

		return R.ok().put("role", sysRoleEntityVo);
	}

	/**
	 *
	 * 功能描述: 新增角色
	 * @param sysRoleEntityVo
	 * @auther: tianms
	 * @date: 2020/02/02 13:16
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public R save(@RequestBody SysRoleEntityVo sysRoleEntityVo) {
		sysRoleEntityVo.setCreateUserId(getUserId()); // 创建者id
		sysRoleService.insert(sysRoleEntityVo);
		return R.ok();
	}

	/**
	 * 修改角色
	 */
	@RequestMapping(value = "/update")
	public R update(@RequestBody SysRoleEntityVo sysRoleEntityVo) {
		sysRoleService.update(sysRoleEntityVo);
		return R.ok();
	}

	/**
	 *
	 * 功能描述: 批量删除角色信息
	 * @param roleIds
	 * @auther: tianms
	 * @date: 2020/02/07 18:30
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/delete")
	public R delete(@RequestBody List<String> roleIds) {

		sysRoleService.deleteBatch(roleIds);

		return R.ok();
	}
}
