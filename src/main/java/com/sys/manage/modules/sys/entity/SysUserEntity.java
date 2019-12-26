package com.sys.manage.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 */
@Data
public class SysUserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * 盐
	 */
	private String salt;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private Integer status;
	
	/**
	 * 创建者ID
	 */
	private String createUserId;

	/**
	 * 创建时间
	 */
	private Date createTime;

}
