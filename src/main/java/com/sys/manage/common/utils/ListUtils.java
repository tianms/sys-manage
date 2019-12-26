package com.sys.manage.common.utils;

import java.util.ArrayList;

/**
 * @Auther: tianms
 * @Date: 2019/12/11 19:57
 * @Description: List通用工具类
 */
public class ListUtils extends ArrayList {

    /**
     *
     * 添加值
     *
     * @Description:
     *
     * @author tianms
     * @date 2019/12/11 19:58
     * @param  str
     * @return com.sys.manage.common.utils.ListUtils
    */
    public ListUtils addVal (String str) {
        super.add(str);
        return this;
    }

}
