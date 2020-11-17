package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.exception.BusinessException;
import com.sys.manage.common.utils.MapUtils;
import com.sys.manage.common.utils.UUIDUtil;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysRoleDao;
import com.sys.manage.modules.sys.entity.vo.SysRoleEntityVo;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 角色信息
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntityVo> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 功能描述: 根据角色id获角色详细信息
     * @param roleId
     * @auther: tianms
     * @date: 2020/02/02 13:09
     * @return com.sys.manage.modules.sys.entity.vo.SysRoleEntityVo
     */
    @Override
    public SysRoleEntityVo queryById (String roleId) {

        // 获取角色的详细信息
        SysRoleEntityVo sysRoleEntityVo = super.queryById(roleId);

        // 获取角色拥有的菜单列表
        List<String> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);

        sysRoleEntityVo.setMenuIdList(menuIdList);

        return sysRoleEntityVo;
    }

    /**
     * 
     * 功能描述: 新增角色信息
     * @param sysRoleEntityVo
     * @auther: tianms
     * @date: 2020/02/02 13:22
     * @return void
     */
    @Override
    @Transactional
    public void insert (SysRoleEntityVo sysRoleEntityVo) {
        // 生成角色id
        sysRoleEntityVo.setRoleId(UUIDUtil.generateID());
        sysRoleEntityVo.setCreateTime(new Date());

        // 新增角色关联的菜单信息
        sysRoleMenuService.insertByList(sysRoleEntityVo.getMenuIdList(), sysRoleEntityVo.getRoleId());

        // 新增角色详细信息
        super.insert(sysRoleEntityVo);
    }

    /**
     * 
     * 功能描述: 更新角色信息
     * @param sysRoleEntityVo
     * @auther: tianms
     * @date: 2020/02/07 17:19
     * @return void
     */
    @Override
    @Transactional
    public void update (SysRoleEntityVo sysRoleEntityVo) {

        // 删除角色关联的元菜单信息
        MapUtils mapUtils = new MapUtils();
        mapUtils.put("roleId", sysRoleEntityVo.getRoleId());
        sysRoleMenuService.delete(mapUtils);

        // 新增角色关联的菜单信息
        sysRoleMenuService.insertByList(sysRoleEntityVo.getMenuIdList(), sysRoleEntityVo.getRoleId());

        // 新增角色详细信息
        super.update(sysRoleEntityVo);
    }

    /**
     *
     * 功能描述: 批量删除角色信息
     * @param roleIds
     * @auther: tianms
     * @date: 2020/02/07 18:59
     * @return void
     */
    @Override
    public void deleteBatch (List<String> roleIds) {

        for (String roleId : roleIds) {

            // 查询角色信息
            SysRoleEntityVo sysRoleEntityVo = super.queryById(roleId);
            if (Constant.SYS_CONSTANT.SUPER_ADMIN.equals(sysRoleEntityVo.getCreateUserId())) {
                throw new BusinessException("超级管理员不可以删除");
            }

            // Step:1 删除角色关联的菜单
            MapUtils mapUtils = new MapUtils();
            mapUtils.put("roleId", roleId);
            sysRoleMenuService.delete(mapUtils);

            // Step:2 删除角色信息
            super.delete(mapUtils);
        }
    }
}
