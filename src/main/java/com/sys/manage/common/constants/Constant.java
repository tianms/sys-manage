package com.sys.manage.common.constants;

/**
 * 基本常量内容
 */
public class Constant {

	/** 系统常量 */
	public class SYS_CONSTANT {

		/** 超级管理员ID，不可修改 */
		public static final String SUPER_ADMIN = "1";

		/** 管理员默认密码 初始 mm111111 此为MD5加密后的 */
		public static final String USER_DEFAULT_PASSWORD = "96e79218965eb72c92a549dd5a330112";

		/** 请求token的表示 */
		public static final String TOKEN = "token";
		/** 请求token超时的表示 */
		public static final String EXPIRE = "expire";
		/*** token超时时间 */
		public static final int TOKEN_EXPIRE = 3600 * 3; // 3小时的超时时间

		/** 系统中表示否定的语气的常量，例如：假，不是... */
		public static final String NO = "0";
		/** 系统中表示肯定的语气的常量，例如：真，是... */
		public static final String YES = "1";

	}

	/**
	 * 菜单静态参数
	 */
	public final class MENU {
		/**
		 * 目录
		 */
		public final static String CATALOG = "0";
		/**
		 * 菜单
		 */
		public final static String MENU = "1";
		/**
		 * 按钮
		 */
		public final static String BUTTON = "2";
		/**
		 * 一级菜单id
		 */
		public final static String ROOTMENUID = "0";
		/**
		 * 一级菜单name
		 */
		public final static String ROOTMENUNAME = "一级菜单";
		/**
		 * 父级菜单id
		 */
		public final static String ROOTMENUPARENTID = "-1";
	}

	/** 账号常量 */
	public class ACCOUNT_CONSTANT {

		// 账号状态
		public class STATUS {

			/** 正常 */
			public static final int NORMAL = 1;
			/** 锁定 */
			public static final int LOCK = 2;

		}

	}

}
