package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.CacheKeyConstant;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.UUIDUtil;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
     * 根据用户id获取用户的详细信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/17 21:16
     * @param  userId
     * @return com.sys.manage.modules.sys.entity.vo.SysUserEntityVo
    */
    @Override
    public SysUserEntityVo queryById (String userId) {
        // 根据用户id获取用户信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        SysUserEntity sysUserEntity = super.queryByMap(map);
        SysUserEntityVo sysUserEntityVo = new SysUserEntityVo();
        BeanUtils.copyProperties(sysUserEntity, sysUserEntityVo);
        // 获取用户的所属角色
        SysUserRoleEntity sysUserRoleEntity = sysUserRoleService.queryByUserId(userId);
        sysUserEntityVo.setRoleId(sysUserRoleEntity.getRoleId());
        return sysUserEntityVo;
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

    /**
     *
     * 新增用户信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/16 20:24
     * @param  sysUserEntityVo
     * @return void
     */
    @Override
    public void insert (SysUserEntityVo sysUserEntityVo) {
        // 生成用户id
        sysUserEntityVo.setUserId(UUIDUtil.generateID());

        // Step:1 添加用户关联的角色信息
        SysUserRoleEntity sysUserRoleEntity = new SysUserRoleEntity();
        sysUserRoleEntity.setId(UUIDUtil.generateID());
        sysUserRoleEntity.setRoleId(sysUserEntityVo.getRoleId());
        sysUserRoleEntity.setUserId(sysUserEntityVo.getUserId());
        sysUserRoleService.insert(sysUserRoleEntity);

        // Step:3 设置用户的基本信息
        sysUserEntityVo.setCreateTime(new Date());
        sysUserEntityVo.setSalt(UUIDUtil.generateID());
        sysUserEntityVo.setPassWord(Constant.SYS_CONSTANT.USER_DEFAULT_PASSWORD); // 设置默认密码

        // Step:3 添加用户基本信息
        super.insert(sysUserEntityVo);
    }

    /**
     *
     * 批量删除用户信息
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/16 22:18
     * @param  ids
     * @return void
    */
    @Override
    public void deleteBatch (List<String> ids) {
        // Step:1 删除用户关联的角色
        Map<String, Object> map = new HashMap<String, Object>();
        for (String userId : ids) {
            map.put("userId", userId);
            sysUserRoleService.delete(map);

            // Step:2 删除缓存中的用户信息
            ehcacheService.removeKey(userId);
        }

        // Step:3 删除用户信息
        super.deleteBatch(ids);
    }
}
