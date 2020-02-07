package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.utils.UUIDUtil;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysRoleMenuDao;
import com.sys.manage.modules.sys.entity.SysRoleMenuEntity;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色与菜单对应关系
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

    /**
     *
     * 功能描述: 获取角色菜单列表，返回菜单id列表
     * @param roleId    角色id
     * @auther: tianms
     * @date: 2020/02/02 13:07
     * @return java.util.List<java.lang.String>
     */
    @Override
    public List<String> queryMenuIdList(String roleId) {
        return baseDao.queryMenuIdList(roleId);
    }

    /**
     *
     * 功能描述: 根据列表和角色id新增
     * @param menuIdList
     * @param roleId
     * @auther: tianms
     * @date: 2020/02/07 17:09
     * @return void
     */
    @Override
    public void insertByList(List<String> menuIdList, String roleId) {
        if (null == menuIdList || menuIdList.size() == 0 || StringUtils.isBlank(roleId)) {
            return;
        }
        for (String menuId : menuIdList) {
            SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
            sysRoleMenuEntity.setId(UUIDUtil.generateID());
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);
            baseDao.insert(sysRoleMenuEntity);
        }
    }
}
