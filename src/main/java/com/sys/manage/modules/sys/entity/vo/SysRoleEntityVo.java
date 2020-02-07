package com.sys.manage.modules.sys.entity.vo;

import com.sys.manage.modules.sys.entity.SysRoleEntity;
import lombok.Data;

import java.util.List;

/**
 * 角色扩展类
 */
@Data
public class SysRoleEntityVo extends SysRoleEntity {

	/**
	 * 角色拥有的菜单id
	 */
	private List<String> menuIdList;

}
