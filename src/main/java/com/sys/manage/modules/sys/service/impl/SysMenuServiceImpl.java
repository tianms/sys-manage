package com.sys.manage.modules.sys.service.impl;

import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.exception.BusinessException;
import com.sys.manage.common.utils.MapUtils;
import com.sys.manage.common.utils.MenuUtils;
import com.sys.manage.common.utils.UUIDUtil;
import com.sys.manage.modules.base.service.impl.BaseServiceImpl;
import com.sys.manage.modules.sys.dao.SysMenuDao;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import com.sys.manage.modules.sys.service.SysMenuService;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntityVo> implements SysMenuService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    /**
     * 根据角色id获取菜单列表
     *
     * @param roleId
     * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
     * @Description:
     * @author tianms
     * @date 2019/12/19 21:13
     */
    @Override
    public List<SysMenuEntityVo> queryMenuListByRoleId(String roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        List<SysMenuEntityVo> sysMenuList = baseDao.queryList(params);
        return sysMenuList;
    }

    /**
     * 获取角色菜单列表(树状菜单列表)
     *
     * @param roleId
     * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
     * @Description: 根据传入的角色id获取角色所拥有的的菜单列表
     * @author tianms
     * @date 2019/12/15 16:31
     */
    @Override
    public List<SysMenuEntityVo> queryRoleMenuList(String roleId) {
        // 根据角色获取角色列表
        List<SysMenuEntityVo> sysMenuList = this.queryFormatMenuListByRoleId(roleId);
        return sysMenuList;
    }

    /**
     * 根据角色id获取格式化后的菜单列表
     * 功能描述:
     *
     * @param roleId
     * @return java.util.List<com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo>
     * @auther: tianms
     * @date: 2019/12/26 09:36
     */
    @Override
    public List<SysMenuEntityVo> queryFormatMenuListByRoleId(String roleId) {

        // 根据角色获取菜单列表
        List<SysMenuEntityVo> sysMenuList = this.queryMenuListByRoleId(roleId);

        // 格式化菜单为页面需要的菜单列表类型
        List<SysMenuEntityVo> sysMenuEntityVo = MenuUtils.formatMenuList(sysMenuList, "");

        return sysMenuEntityVo;
    }


    /**
     * 获取角色的权限
     * 功能描述:
     *
     * @param roleId 角色id
     * @return java.util.List<java.lang.String>
     * @auther: tianms
     * @date: 2019/12/26 09:27
     */
    @Override
    public List<String> queryPerMissionsByRoleId(String roleId) {
        List<String> perMissionList = new ArrayList<String>();

        // 获取角色的菜单列表
        List<SysMenuEntityVo> sysMenuList = this.queryMenuListByRoleId(roleId);

        for (SysMenuEntityVo sysMenuEntityVo : sysMenuList) {
            if (StringUtils.isNotBlank(sysMenuEntityVo.getPerms())) {
                perMissionList.add(sysMenuEntityVo.getPerms());
            }
        }
        return perMissionList;

    }

    /**
     *
     * 获取不包括按钮菜单列表（主要用于菜单编辑选择上级菜单使用）
     *
     * @Description:
     *
     * @author tianms
     * @date 2020/01/18 22:01
     * @param
     * @return java.util.List<com.sys.manage.modules.sys.entity.SysMenuEntity>
     */
    @Override
    public List<SysMenuEntity> queryNotButtonList() {
        return baseDao.queryNotButtonList();
    }

    /**
     * 菜单新增
     * 功能描述:
     * @param sysMenuEntityVo
     * @auther: tianms
     * @date: 2020/01/23 13:22
     * @return void
     */
    @Override
    public void insert(SysMenuEntityVo sysMenuEntityVo) {

        // 校验新增的菜单信息是否正确
        this.verifyForm(sysMenuEntityVo);

        sysMenuEntityVo.setMenuId(UUIDUtil.generateID()); // 生成菜单id
        sysMenuEntityVo.setIsSysMenu(Constant.SYS_CONSTANT.NO); // 不是系统菜单

        // 新增菜单信息
        super.insert(sysMenuEntityVo);
    }

    /**
     * 菜单修改
     * 功能描述:
     * @param sysMenuEntityVo
     * @auther: tianms
     * @date: 2020/01/23 13:22
     * @return void
     */
    @Override
    public void update(SysMenuEntityVo sysMenuEntityVo) {

        // 校验新增的菜单信息是否正确
        this.verifyForm(sysMenuEntityVo);

        // 修改菜单信息
        super.update(sysMenuEntityVo);
    }


    /**
     * 验证菜单信息中参数是否正确
     * 功能描述:
     * @param sysMenuEntityVo
     * @auther: tianms
     * @date: 2020/01/23 13:25
     * @return void
     */
    private void verifyForm(SysMenuEntityVo sysMenuEntityVo) {
        if (StringUtils.isBlank(sysMenuEntityVo.getName())) {
            throw new BusinessException("菜单名称不能为空");
        }

        if (sysMenuEntityVo.getParentId() == null) {
            throw new BusinessException("上级菜单不能为空");
        }
    }

    /**
     * 根据id获取菜单详细信息
     * 功能描述:
     * @param menuId
     * @auther: tianms
     * @date: 2020/01/28 15:20
     * @return com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo
     */
    @Override
    public SysMenuEntityVo queryById(String menuId) {

        // 如果查询的是一级目录信息或者id为空，返回空
        if (Constant.MENU.ROOTMENUID.equals(menuId) || StringUtils.isBlank(menuId)) {
            return null;
        }

        // 获取菜单信息
        SysMenuEntityVo sysMenuEntityVo = super.queryById(menuId);

        if (Constant.MENU.ROOTMENUID.equals(sysMenuEntityVo.getParentId())) { // 一级菜单
            sysMenuEntityVo.setParentMenuName(Constant.MENU.ROOTMENUNAME);
        } else { // 上级菜单不是一级目录
            // 获取上级菜单信息
            SysMenuEntityVo parentMenuEntity = super.queryById(sysMenuEntityVo.getParentId());
            sysMenuEntityVo.setParentMenuName(parentMenuEntity.getName()); // 上级菜单名称
        }
        return sysMenuEntityVo;
    }

    /**
     *
     * 批量删除菜单
     *
     * 功能描述:
     * @param ids
     * @auther: tianms
     * @date: 2020/01/28 18:23
     * @return void
     */
    @Override
    public void deleteBatch (List<String> ids) {

        if (null == ids || ids.size() == 0) {
            return;
        }

        for (String menuId : ids) {
            // 获取要删除的菜单信息
            SysMenuEntityVo sysMenuEntityVo = this.queryById(menuId);

            if (Constant.SYS_CONSTANT.YES.equals(sysMenuEntityVo.getIsSysMenu())) { // 如果是系统菜单，不可以删除
                throw new BusinessException("菜单\"" + sysMenuEntityVo.getName() + "\"为系统菜单，不可删除");
            }

            // 根据菜单查询是否有子菜单或者按钮
            MapUtils map = new MapUtils();
            map.put("parentId", menuId);
            List<SysMenuEntityVo> sysMenuEntityList = this.queryList(map);
            if (sysMenuEntityList != null && sysMenuEntityList.size() > 0) {
                throw new BusinessException("请先删除菜单\"" + sysMenuEntityVo.getName() + "\"下的菜单和按钮");
            }

            map.put("menuId", menuId);
            // 删除角色关联的菜单
            sysRoleMenuService.delete(map);

            super.delete(menuId);
        }
    }
}
