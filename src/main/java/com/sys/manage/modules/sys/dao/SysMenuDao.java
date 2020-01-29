package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntityVo> {

	/**
	 *
	 * 获取不包括按钮菜单列表（主要用于菜单编辑选择上级菜单使用）
	 *
	 * @Description:
	 *
	 * @author tianms
	 * @date 2020/01/18 22:01
	 * @param
	 * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
	 */
	List<SysMenuEntity> queryNotButtonList();

}
