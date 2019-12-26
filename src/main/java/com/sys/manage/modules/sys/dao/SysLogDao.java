package com.sys.manage.modules.sys.dao;

import com.sys.manage.modules.base.dao.BaseDao;
import com.sys.manage.modules.sys.entity.SysLogEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 */
@Mapper
public interface SysLogDao extends BaseDao<SysLogEntity> {
	
}
