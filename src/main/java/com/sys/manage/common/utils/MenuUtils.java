package com.sys.manage.common.utils;

import com.sys.manage.common.enums.MenuEnum;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianms
 * @Date 2019/12/25 17:20
 * 菜单工具类
 */
public class MenuUtils {

    /**
     * 格式化菜单列表
     * 功能描述: 根据传入的菜单列表格式化菜单，菜单的子菜单在对象的list属性中
     *  根据类型定制返回的菜单列表显示格式
     *      目录List-->菜单List
     *      目录List-->菜单List-->按钮
     * @param sysMenuList   需要格式化的菜单列表
     * @auther: tianms
     * @date: 2019/12/25 17:22
     * @return java.util.List<com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo>
     */
    public static List<SysMenuEntityVo> formatMenuList (List<SysMenuEntityVo> sysMenuList, String type) {

        // 返回的菜单列表
        List<SysMenuEntityVo> resMenuList = new ArrayList<SysMenuEntityVo>();

        for (SysMenuEntityVo sysMenuEntityVo : sysMenuList) {

            if (MenuEnum.CATALOG.getValue().equals(sysMenuEntityVo.getParentId())) { // 目录

                // 子菜单列表
                List<SysMenuEntityVo> childMenuList = getChildMenuList(sysMenuList, sysMenuEntityVo, type);
                sysMenuEntityVo.setList(childMenuList); // 设置子菜单列表
                resMenuList.add(sysMenuEntityVo);
            }

        }

        return resMenuList;
    }

    /**
     * 获取子菜单列表
     * 功能描述:
     * @param menuList
     * @param parentMenu
     * @param type
     * @auther: tianms
     * @date: 2019/12/25 17:31
     * @return java.util.List<com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo>
     */
    private static List<SysMenuEntityVo> getChildMenuList (List<SysMenuEntityVo> menuList, SysMenuEntityVo parentMenu, String type) {

        // 子菜单列表
        List<SysMenuEntityVo> childMenuList = new ArrayList<SysMenuEntityVo>();

        for (SysMenuEntityVo sysMenuEntityVo : menuList) {

            // 如果当前菜单的父菜单id是传入的父菜单信息中的菜单id，继续获取下级菜单
            if (parentMenu.getMenuId().equals(sysMenuEntityVo.getParentId())) {
                if (sysMenuEntityVo.getType() == 2) { // 按钮
                    continue;
                } else { // 不是按钮，就是菜单
                    List<SysMenuEntityVo> nextChildMenuList =  getChildMenuList(menuList, sysMenuEntityVo, type);
                    sysMenuEntityVo.setList(nextChildMenuList);
                    childMenuList.add(sysMenuEntityVo);
                }
            }
        }

        return childMenuList;

    }

}
