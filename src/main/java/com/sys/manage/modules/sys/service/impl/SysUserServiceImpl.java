package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysUserDao;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    private static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return com.sys.manage.modules.sys.entity.SysUserEntity
     * @Description:
     * @author tianms
     * @date 2019/12/10 22:14
     */
    @Override
    public SysUserEntity queryByUserName(String userName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        return baseDao.queryByMap(map);
    }
}
