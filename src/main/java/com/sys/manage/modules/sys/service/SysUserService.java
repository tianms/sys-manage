package com.sys.manage.modules.sys.service;

import com.github.pagehelper.PageInfo;
import com.sys.manage.modules.base.service.BaseService;
import com.sys.manage.modules.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;


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
	 * 根据条件查询对应实体信息
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/10 20:20
	 * @param  map
	 * @return T
	 */
	SysUserEntity queryByMap(Map<String, Object> map);


	/**
	 *
	 * 根据条件获取列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/15 21:50
	 * @param  params
	 * @return java.util.List<T>
	 */
	List<SysUserEntity> queryList(Map<String, Object> params);

	/**
	 *
	 * 根据条件获取分页列表
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2019/12/15 21:50
	 * @param  params
	 * @return com.github.pagehelper.PageInfo<T>
	 */
	PageInfo<SysUserEntity> queryPage(Map<String, Object> params);

}
