package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysLogDao;
import com.sys.manage.modules.sys.entity.SysLogEntity;
import com.sys.manage.modules.sys.service.SysLogService;
import org.springframework.stereotype.Service;

@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLogDao, SysLogEntity> implements SysLogService {

}
