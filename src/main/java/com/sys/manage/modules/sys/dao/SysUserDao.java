package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

}
