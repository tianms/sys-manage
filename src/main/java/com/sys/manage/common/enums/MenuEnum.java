package com.sys.manage.common.enums;

/**
 * @Auther: tianms
 * @Date: 2019/12/10 22:03
 * @Description: 菜单类型枚举
 */
public enum MenuEnum {

    /**
     * 目录
     */
    CATALOG("0"),
    /**
     * 菜单
     */
    MENU("1"),
    /**
     * 按钮
     */
    BUTTON("2");

    private String value;

    MenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
