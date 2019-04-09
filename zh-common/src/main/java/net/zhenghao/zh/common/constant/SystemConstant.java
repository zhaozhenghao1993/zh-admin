package net.zhenghao.zh.common.constant;
/**
 * 系统级静态变量
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年12月6日 下午2:17:36
 * SystemConstant.java
 */
public class SystemConstant {
	
	/**
	 * 超级管理员ID
	 */
	public static final long SUPER_ADMIN = 1;
	
	/**
	 * 数据标识
	 */
	public static final String DATA_ROWS = "data";


	/**
	 * 当前用户ID key
	 */
	public static final String CONTEXT_KEY_USER_ID = "currentUserId";

	/**
	 * 当前用户username key
	 */
	public static final String CONTEXT_KEY_USERNAME = "currentUserName";

	/**
	 * 当前用户token key
	 */
	public static final String CONTEXT_KEY_USER_TOKEN = "currentUserToken";

	/**
	 * JWT userId标识
	 */
	public static final String JWT_KEY_USER_ID = "userId";

	/**
	 * JWT username标识
	 */
	public static final String JWT_KEY_USERNAME = "username";

	/**
	 * 日志类型
	 */
	public enum LogType {
		
		/**
		 * 登录登出日志
		 */
		LOGIN(1),

		/**
		 * 访问日志
		 */
		ACCESS(2),

		/**
		 * 操作日志
		 */
		OPERATION(3),

		/**
		 * 异常日志
		 */
		ERROR(4),

		/**
		 * 授权日志
		 */
		AUTHORIZATION(5);
		
		private int value;
		
		private LogType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
		
	}
	
	/**
	 * 通用变量,表示 可用、禁用、显示、隐藏
	 *
	 * @author:zhaozhenghao
	 * @Email :736720794@qq.com
	 * @date  :2017年12月6日 下午2:17:36
	 * SystemConstant.java
	 */
	public enum StatusType {
		
		/**
		 * 可用,显示,成功
		 */
		ENABLE(0),
		
		/**
		 * 禁用,隐藏,失败,锁定
		 */
		DISABLE(1),
		
		/**
		 * 显示
		 */
		SHOW(0),
		
		/**
		 * 隐藏
		 */
		HIDDEN(1);
		
		private int value;
		
		private StatusType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	
	
	
	
	/**
	 * 菜单类型
	 *
	 * @author:zhaozhenghao
	 * @Email :736720794@qq.com
	 * @date  :2017年12月6日 下午4:29:36
	 * SystemConstant.java
	 */
	public enum MenuType {
		
		/**
		 * 目录
		 */
		CATALOG(0),
		
		/**
		 * 菜单
		 */
		MENU(1),
		
		/**
		 * 按钮
		 */
		BUTTON(2);
		
		private int value;
		
		private MenuType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	/**
	 * 定时任务状态
	 * 
	 * @author:zhaozhenghao
	 * @Email :736720794@qq.com
	 * @date  :2017年12月6日 下午4:29:36
	 * SystemConstant.java
	 */
	public enum ScheduleStatus {
		/**
		 * 正常
		 */
		NORMAL(1),
		/**
		 * 暂停
		 */
		PAUSE(0);
		
		private int value;
		
		private ScheduleStatus(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	
	/**
	 * 上传文件类型
	 * 
	 * @author:zhaozhenghao
	 * @Email :736720794@qq.com
	 * @date  :2018年2月9日 上午11:17:36
	 * FileType.java
	 */
	public enum FileType {
		/**
		 * 图片
		 */
		IMAGE(1),
		/**
		 * 文档
		 */
		DOCUMENT(2),
		/**
		 * 视频
		 */
		VIDEO(3),
		/**
		 * 种子
		 */
		SEED(4),
		/**
		 * 音乐
		 */
		MUSIC(5),
		/**
		 * 其他
		 */
		OTHER(6);
		
		private int value;
		
		private FileType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	
	/**
	 * 上传方式类型
	 * 
	 * @author:zhaozhenghao
	 * @Email :736720794@qq.com
	 * @date  :2018年2月9日 上午11:17:36
	 * FileType.java
	 */
	public enum UploadType {
		/**
		 * 普通上传
		 */
		COMMON(1),
		/**
		 * 秒传
		 */
		SECOND(2);
		
		private int value;
		
		private UploadType(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
