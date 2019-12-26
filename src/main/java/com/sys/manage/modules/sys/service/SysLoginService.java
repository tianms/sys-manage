package com.sys.manage.modules.sys.service;

import com.sys.manage.common.utils.R;
import com.sys.manage.modules.common.service.entity.SysLoginEntity;

/**
 * @Auther: tianms
 * @Date: 2019/12/10 22:08
 * @Description: 登录接口类
 */
public interface SysLoginService {

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
    R login(SysLoginEntity sysLoginEntity);


}
