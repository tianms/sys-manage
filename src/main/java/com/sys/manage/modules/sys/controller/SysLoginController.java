package com.sys.manage.modules.sys.controller;

import java.io.IOException;
import java.util.Map;

import com.sys.manage.modules.base.entity.SysLoginEntity;
import com.sys.manage.modules.sys.service.SysLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sys.manage.common.utils.R;

/**
 * 登录相关
 */
@RestController
public class SysLoginController extends AbstractController {

	private Logger logger = LoggerFactory.getLogger(SysLoginController.class);

	@Autowired
	private SysLoginService sysLoginService;

	/**
	 * @Description: 用户登录
	 *
	 * @author tianms
	 * @date 2019/12/10 20:04
	 * @param  sysLoginEntity
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	*/
	@PostMapping("/sys/login")
	public Map<String, Object> login(@RequestBody SysLoginEntity sysLoginEntity) throws IOException {

		R r = sysLoginService.login(sysLoginEntity);

		return r;
	}


	/**
	 * 退出
	 */
	@PostMapping("/sys/logout")
	public R logout() {
		return R.ok();
	}
	
}
