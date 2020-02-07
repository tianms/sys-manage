package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.entity.vo.SysRoleEntityVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntityVo> {
}
