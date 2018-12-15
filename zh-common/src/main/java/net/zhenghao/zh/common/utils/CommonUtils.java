package net.zhenghao.zh.common.utils;

import net.zhenghao.zh.common.constant.MsgConstant;
import net.zhenghao.zh.common.constant.SystemConstant;
import net.zhenghao.zh.common.entity.R;

/**
 * 通用工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date  :2017年11月22日 下午2:43:47
 * CommonUtils.java
 */
public class CommonUtils {
	
	/**
	 * 判断整数是否大于零
	 * @param number
	 * @return
	 */
	public static boolean isIntThanZero(int number){
		if (number > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 新增,修改提示
	 * @param count
	 * @return
	 */
	public static R msg(int count) {
		if (isIntThanZero(count)) {
			return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}
		return R.error(MsgConstant.MSG_OPERATION_FAILED);
	}
	
	
	/**
	 * 查询详情提示
	 * @param data
	 * @return
	 */
	public static R msg(Object data) {
		if (isNullOrEmpty(data)) {
			return R.error(MsgConstant.MSG_INIT_FORM);
		}
		return R.ok().put(SystemConstant.DATA_ROWS, data);
	}
	
	/**
	 * 返回数据
	 * @param data
	 * @return
	 */
	public static R msgNotNull(Object data) {
		return R.ok().put(SystemConstant.DATA_ROWS, data);
	}
	
 
	/**
	 * 删除提示
	 * @param total
	 * @param count
	 * @return
	 */
	public static R msg(Object[] total, int count){
		if (total.length == count) {
			return R.ok(MsgConstant.MSG_OPERATION_SUCCESS);
		}else {
			if (isIntThanZero(count)) {
				return R.error(MsgConstant.removeFailed(total.length, count));
			}else {
				return R.error(MsgConstant.MSG_OPERATION_FAILED);
			}
		}
	}
	
	
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
}
