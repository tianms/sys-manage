package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.CacheKeyConstant;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.exception.BusinessException;
import com.sys.manage.common.utils.Base64Utils;
import com.sys.manage.common.utils.R;
import com.sys.manage.common.utils.TokenUtils;
import com.sys.manage.config.EhcacheService;
import com.sys.manage.modules.base.entity.SysLoginEntity;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.SysUserRoleEntity;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import com.sys.manage.modules.sys.service.SysLoginService;
import com.sys.manage.modules.sys.service.SysUserRoleService;
import com.sys.manage.modules.sys.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: tianms
 * @Date: 2019/12/10 22:08
 * @Description: 登录接口实现
 */
@Service("sysLoginService")
public class SysLoginServiceImpl implements SysLoginService {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private EhcacheService ehcacheService;

    /**
     *
     * 登录
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/10 22:12
     * @param  sysLoginEntity
     * @return com.sys.manage.common.utils.R
     */
    @Override
    public R login(SysLoginEntity sysLoginEntity) {

        // 对用户名解码
        String userName = Base64Utils.decode(sysLoginEntity.getUserName());

        // 根据用户名获取用户信息
        SysUserEntity sysUserEntity = sysUserService.queryByUserName(userName);

        //账号不存在、密码错误
        if(null == sysUserEntity || !sysUserEntity.getPassWord().equals(sysLoginEntity.getPassWord())) {
            return R.error("账号或密码不正确");
        }

        //账号锁定
        if(sysUserEntity.getStatus().intValue() == Constant.ACCOUNT_CONSTANT.STATUS.LOCK) {
            return R.error("账号已被锁定,请联系管理员");
        }

        String userId = sysUserEntity.getUserId(); // 用户id

        // 登录成功，生成token，保存到缓存中
        String token = TokenUtils.makeToken();
        ehcacheService.putWithTime(CacheKeyConstant.USER_TOKEN_KEY + token, userId, Constant.SYS_CONSTANT.TOKEN_EXPIRE);

        SysUserEntityVo sysUserEntityVo = new SysUserEntityVo();
        BeanUtils.copyProperties(sysUserEntity, sysUserEntityVo);

        // 获取角色信息
        SysUserRoleEntity sysUserRoleEntity = sysUserRoleService.queryByUserId(sysUserEntityVo.getUserId());
        if (sysUserRoleEntity == null) {
            throw new BusinessException("请联系管理员配置用户角色");
        }
        sysUserEntityVo.setRoleId(sysUserRoleEntity.getRoleId());

        // 将用户信息对应的用户id保存到缓存中
        ehcacheService.putWithTime(CacheKeyConstant.USER_INFO_KEY + userId, sysUserEntityVo, Constant.SYS_CONSTANT.TOKEN_EXPIRE);

        // 将生成的token返回到前端
        return R.ok().put(Constant.SYS_CONSTANT.TOKEN, token).put(Constant.SYS_CONSTANT.EXPIRE, Constant.SYS_CONSTANT.TOKEN_EXPIRE);
    }

}
