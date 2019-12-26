package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

//	/**
//	 * 查询用户创建的角色ID列表
//	 */
//	List<Long> queryRoleIdList(Long createUserId);
//
//	/**
//	 * 查询所有角色列表
//	 *
//	 * @Title: queryAllRole
//	 * @Description: TODO(这里用一句话描述这个方法的作用)
//	 * @param @return
//	 *            设定文件
//	 * @return List<SysRoleEntity> 返回类型
//	 * @author tianms
//	 * @throws @date
//	 *             2018年6月12日 下午2:26:45
//	 */
//	List<SysRoleEntity> queryAllRole();
}
