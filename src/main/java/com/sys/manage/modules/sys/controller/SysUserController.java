package com.sys.manage.modules.sys.controller;

import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.common.service.entity.PasswordForm;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);
	
	@Autowired
	private SysUserService sysUserService;

	/**
	 *
	 * 获取登录的用户信息
	 *
	 * @Description: 获取登录用户的信息
	 *
	 * @author tianms
	 * @date 2019/12/15 16:16
	 * @param
	 * @return com.sys.manage.common.utils.R
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public R info () {
		// 从缓存中获取用户的信息
		return R.ok().put("user", getUser());
	}

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
//		if(getUserId() != Constant.SYS_CONSTANT.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}
//		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", null);
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		//sha256加密
		String password = form.getPassword();
		//sha256加密
		String newPassword = form.getNewPassword();

		//更新密码
//		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
//		if(!flag){
//			return R.error("原密码不正确");
//		}
		
		return R.ok();
	}
	
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	public R save(@RequestBody SysUserEntity user){
//		user.setCreateUserId(getUserId());
//		user.setPassword(Constant.USER_DEFAULT_PASSWORD);// 设置新增用户默认密码111111
//		sysUserService.save(user);
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	public R update(@RequestBody SysUserEntity user){
//		user.setCreateUserId(getUserId());
//		sysUserService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
//		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
