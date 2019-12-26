package com.sys.manage.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色与菜单对应关系
 */
@Data
public class SysRoleMenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 菜单ID
	 */
	private String menuId;


}
