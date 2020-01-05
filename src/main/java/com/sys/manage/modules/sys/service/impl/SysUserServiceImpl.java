package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.CacheKeyConstant;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.config.EhcacheService;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysUserDao;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.SysUserRoleEntity;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import com.sys.manage.modules.sys.service.SysUserRoleService;
import com.sys.manage.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    private static Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private EhcacheService ehcacheService;

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

    /**
     *
     * 更新用户信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/01 21:15
     * @param  sysUserEntityVo
     * @return void
    */
    @Override
    public void update (SysUserEntityVo sysUserEntityVo) {
        // Step:1 更新用户关联的角色信息
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setUserId(sysUserEntityVo.getUserId());
        sysUserRoleEntity.setRoleId(sysUserEntityVo.getRoleId());
        sysUserRoleService.update(sysUserRoleEntity);

        // Step:2 更新用户信息
        super.update(sysUserEntityVo);

        // Step:3 将用户信息更新到缓存中
        ehcacheService.putWithTime(CacheKeyConstant.USER_INFO_KEY + sysUserEntityVo.getUserId(), sysUserEntityVo, Constant.SYS_CONSTANT.TOKEN_EXPIRE);
    }
}
