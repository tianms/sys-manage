package com.sys.manage;

import com.alibaba.fastjson.JSONObject;
import com.sys.manage.common.utils.UUIDUtil;
import com.sys.manage.modules.sys.dao.SysRoleDao;
import com.sys.manage.modules.sys.entity.SysMenuEntity;
import com.sys.manage.modules.sys.entity.SysRoleEntity;
import com.sys.manage.modules.sys.entity.SysRoleMenuEntity;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.vo.SysMenuEntityVo;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import com.sys.manage.modules.sys.service.SysMenuService;
import com.sys.manage.modules.sys.service.SysRoleMenuService;
import com.sys.manage.modules.sys.service.SysRoleService;
import com.sys.manage.modules.sys.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: tianms
 * @Date: 2019/12/15 16:37
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestClass {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Test
    public void testCopyClass () {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setCreateTime(new Date());
        SysUserEntityVo sysUserEntityVo = new SysUserEntityVo();
        BeanUtils.copyProperties(sysUserEntity, sysUserEntityVo);
        System.out.println(JSONObject.toJSONString(sysUserEntityVo));
    }


    @Test
    public void initBaseData () {

       List<SysRoleEntity> sysRoleEntities  = sysRoleService.queryList(new HashMap<String, Object>());

       List<SysMenuEntity> sysMenuEntities = sysMenuService.queryList(new HashMap<String, Object>());

       for (SysRoleEntity sysRoleEntity : sysRoleEntities) {


           for (SysMenuEntity sysMenuEntity : sysMenuEntities) {

               SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();

               sysRoleMenuEntity.setId(UUIDUtil.generateID());
               sysRoleMenuEntity.setMenuId(sysMenuEntity.getMenuId());
               sysRoleMenuEntity.setRoleId(sysRoleEntity.getRoleId());

               sysRoleMenuService.insert(sysRoleMenuEntity);
           }

       }
    }

    @Test
    public void testQueryMenuList () {
        List<SysMenuEntity> sysMenuEntities = sysMenuService.queryList(new HashMap<String, Object>());
        List<SysMenuEntityVo> sysMenuEntityVos = this.getList(sysMenuEntities);

        for (SysMenuEntityVo sysMenuEntityVo : sysMenuEntityVos) {
            System.err.println(JSONObject.toJSONString(sysMenuEntityVo));
            System.out.println(JSONObject.toJSONString(sysMenuEntityVo.getList()));
        }
    }

    private List<SysMenuEntityVo> getList (List<SysMenuEntity> sysMenuList) {

        List<SysMenuEntityVo> resMenuList = new ArrayList<SysMenuEntityVo>();

        for (SysMenuEntity sysMenuEntity : sysMenuList) {

            // 顶级菜单，获取下面的子菜单
            if ("0".equals(sysMenuEntity.getParentId())) {

                SysMenuEntityVo sysMenuEntityVo = new SysMenuEntityVo();
                BeanUtils.copyProperties(sysMenuEntity, sysMenuEntityVo);


                System.err.println("0=====" + JSONObject.toJSONString(sysMenuEntityVo));
                // 子菜单列表
                List<SysMenuEntityVo> childMenuList = getChildMenuList(sysMenuList, sysMenuEntityVo);
                sysMenuEntityVo.setList(childMenuList);
                resMenuList.add(sysMenuEntityVo);
            }

        }

        return resMenuList;
    }

    private List<SysMenuEntityVo> getChildMenuList (List<SysMenuEntity> menuList, SysMenuEntityVo parentMenu) {

        // 子菜单列表
        List<SysMenuEntityVo> childMenuList = new ArrayList<SysMenuEntityVo>();

        for (SysMenuEntity sysMenuEntity : menuList) {

            // 如果当前菜单的父菜单id是传入的父菜单信息中的菜单id，继续获取下级菜单
            if (parentMenu.getMenuId().equals(sysMenuEntity.getParentId())) {
                SysMenuEntityVo sysMenuEntityVo = new SysMenuEntityVo();
                BeanUtils.copyProperties(sysMenuEntity, sysMenuEntityVo);

                System.out.println("child===" + JSONObject.toJSONString(sysMenuEntityVo));

                List<SysMenuEntityVo> nextChildMenuList = getChildMenuList(menuList, sysMenuEntityVo);
                sysMenuEntityVo.setList(nextChildMenuList);
                childMenuList.add(sysMenuEntityVo);
            }
        }

        return childMenuList;

    }

}
