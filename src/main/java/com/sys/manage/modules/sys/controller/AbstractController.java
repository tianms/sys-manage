package com.sys.manage.modules.sys.controller;

import com.sys.manage.common.constants.CacheKeyConstant;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.exception.BusinessException;
import com.sys.manage.config.EhcacheService;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller公共组件
 */
public abstract class AbstractController {

	@Autowired
	private EhcacheService ehcacheService;

	/**
	 * 请求信息
	 */
	protected HttpServletRequest request;

	/**
	 * 相应信息
	 */
	protected HttpServletResponse response;

	// 设置请求信息
	@ModelAttribute
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	// 设置响应信息
	@ModelAttribute
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	// 获取请求头中的token
	protected String getAccessToken () {
		return request.getHeader(Constant.SYS_CONSTANT.TOKEN);
	}


	/**
	 *
	 * 获取用户id，根据token获取用户id
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/11 20:01
	 * @param
	 * @return java.lang.String
	*/
	protected String getUserId () {
		Object object = ehcacheService.get(CacheKeyConstant.USER_TOKEN_KEY + getAccessToken());
		return String.valueOf(object);
	}

	/**
	 *
	 * 获取登录用户信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/11 20:08
	 * @param
	 * @return com.sys.manage.modules.sys.entity.SysUserEntity
	*/
	protected SysUserEntityVo getUser () {
		Object object = ehcacheService.get(CacheKeyConstant.USER_INFO_KEY + getUserId());
		return object == null ? null : (SysUserEntityVo) object;
	}

	/**
	 *
	 * 获取用户所属角色id
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/19 20:59
	 * @param
	 * @return java.lang.String
	*/
	protected String getRoleId () {
		String roleId = this.getUser().getRoleId();
		if (StringUtils.isBlank(roleId)) {
			throw new BusinessException("请联系管理员配置用户角色");
		}
		return roleId;
	}

}
