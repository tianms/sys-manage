package com.sys.manage.modules.sys.controller;

import com.github.pagehelper.PageInfo;
import com.sys.manage.common.annotation.SysLog;
import com.sys.manage.common.constants.Constant;
import com.sys.manage.common.utils.R;
import com.sys.manage.modules.base.entity.PasswordForm;
import com.sys.manage.modules.sys.entity.SysUserEntity;
import com.sys.manage.modules.sys.entity.vo.SysUserEntityVo;
import com.sys.manage.modules.sys.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

    private Logger logger = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取登录的用户信息
     *
     * @param
     * @return com.sys.manage.common.utils.R
     * @Description: 获取登录用户的信息
     * @author tianms
     * @date 2019/12/15 16:16
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public R info(@RequestBody Map<String, Object> params) {
        SysUserEntityVo sysUserEntityVo = getUser(); // 默认从缓存中获取当前登录的用户信息
        if (params != null && null != params.get("id")) { // 如果传入了id,则根据用户id获取用户信息
            sysUserEntityVo = sysUserService.queryById(String.valueOf(params.get("id")));
        }
        // 从缓存中获取用户的信息
        return R.ok().put("user", sysUserEntityVo);
    }

    /**
     * 用户列表
     *
     * @param params
     * @return com.sys.manage.common.utils.R
     * @Description:
     * @author tianms
     * @date 2020/01/01 20:14
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public R list(@RequestBody Map<String, Object> params) {

        PageInfo pageInfo = sysUserService.queryPage(params);

        return R.ok().put("page", pageInfo);
    }

    /**
     * 添加用户
     *
     * @param sysUserEntityVo
     * @return com.sys.manage.common.utils.R
     * @Description:
     * @author tianms
     * @date 2020/01/16 21:59
     */
    @RequestMapping(value = "/save")
    public R save(@RequestBody SysUserEntityVo sysUserEntityVo) {
        sysUserEntityVo.setCreateUserId(getUserId());
        sysUserService.insert(sysUserEntityVo);
        return R.ok();
    }

    /**
     * 修改用户信息
     *
     * @param sysUserEntityVo
     * @return com.sys.manage.common.utils.R
     * @Description:
     * @author tianms
     * @date 2020/01/16 21:59
     */
    @RequestMapping(value = "/update")
    public R update(@RequestBody SysUserEntityVo sysUserEntityVo) {
        sysUserService.update(sysUserEntityVo);
        return R.ok();
    }

    /**
     * 删除用户
     *
     * @param userIds
     * @return com.sys.manage.common.utils.R
     * @Description:
     * @author tianms
     * @date 2020/01/16 22:00
     */
    @RequestMapping(value = "/delete")
    public R delete(@RequestBody List<String> userIds) {
        if (userIds.contains(Constant.SYS_CONSTANT.SUPER_ADMIN)) {
            return R.error("supper用户不可删除哟~");
        }
        if (userIds.contains(getUserId())) {
            return R.error("当前登录用户不能删除");
        }

        // 批量删除用户
        sysUserService.deleteBatch(userIds);

        return R.ok();
    }

    /**
     * 修改登录用户密码
     */
    @SysLog("修改密码")
    @PostMapping("/password")
    public R password(@RequestBody PasswordForm form) {
        //sha256加密
        String password = form.getPassword();
        //sha256加密
        String newPassword = form.getNewPassword();

        //更新密码
//		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
//		if(!flag){
//			return R.error("原密码不正确");
//		}

        return R.ok();
    }


}
