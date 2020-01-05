package com.sys.manage.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.base.entity.PasswordForm;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import com.sys.manage.modules.sys.service.SysUserService;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	 *
	 * 用户列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/01 20:14
	 * @param  params
	 * @return com.sys.manage.common.utils.R
	*/
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public R list(@RequestBody Map<String, Object> params){

		PageInfo pageInfo = sysUserService.queryPage(params);

		return R.ok().put("page", pageInfo);
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
	public R save(@RequestBody SysUserEntity sysUserEntity){
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
	public R update(@RequestBody SysUserEntityVo sysUserEntityVo){
		sysUserService.update(sysUserEntityVo);
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
