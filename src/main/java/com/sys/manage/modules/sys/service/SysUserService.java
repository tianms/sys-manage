package com.sys.manage.modules.sys.service;

import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;


/**
 * 系统用户
 */
public interface SysUserService extends BaseService<SysUserEntity> {

	/**
	 *
	 * 根据用户名获取用户信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/10 22:14
	 * @param  userName
	 * @return com.sys.manage.modules.sys.entity.SysUserEntity
	*/
	SysUserEntity queryByUserName(String userName);

	/**
	 *
	 * 更新用户信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/01 21:14
	 * @param  sysUserEntityVo
	 * @return void
	 */
	void update (SysUserEntityVo sysUserEntityVo);

	/**
	 *
	 * 新增用户信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/16 20:24
	 * @param  sysUserEntityVo
	 * @return void
	*/
	void insert (SysUserEntityVo sysUserEntityVo);


	/**
	 *
	 * 根据用户id获取用户的详细信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/17 21:16
	 * @param  userId
	 * @return com.sys.manage.modules.sys.entity.vo.SysUserEntityVo
	 */
	SysUserEntityVo queryById (String userId);

}
