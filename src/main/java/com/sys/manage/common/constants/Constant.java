package com.sys.manage.common.constants;

/**
 * 基本常量内容
 */
public class Constant {

	/** 系统常量 */
	public class SYS_CONSTANT {

		/** 超级管理员ID，不可修改 */
		public static final int SUPER_ADMIN = 1;

		/** 管理员默认密码 初始 mm111111 此为MD5加密后的 */
		public static final String USER_DEFAULT_PASSWORD = "111111";

		/** 请求token的表示 */
		public static final String TOKEN = "token";
		/** 请求token超时的表示 */
		public static final String EXPIRE = "expire";

		/*** token超时时间 */
		public static final int TOKEN_EXPIRE = 3600 * 3; // 3小时的超时时间
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
