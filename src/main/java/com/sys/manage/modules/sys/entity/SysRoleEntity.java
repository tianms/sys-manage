package com.sys.manage.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色
 */
@Data
public class SysRoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	private String roleId;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建者ID
	 */
	private String createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
