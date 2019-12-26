package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

	/**
	 * 根据父菜单，查询子菜单
	 * 
	 * @param parentId
	 *            父菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId);

	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();

	/**
	 * 查询包含列表的菜单
	 * 
	 * @Title: queryContainListMenu
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return
	 *            设定文件
	 * @return List<SysMenuEntity> 返回类型
	 * @author tianms
	 * @throws @date
	 *             2018年6月11日 下午3:45:25
	 */
	List<SysMenuEntity> queryContainListMenu();
}
