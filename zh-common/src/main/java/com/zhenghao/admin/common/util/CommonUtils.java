package com.zhenghao.admin.common.util;

import com.zhenghao.admin.common.constant.MsgConstant;
import com.zhenghao.admin.common.entity.Result;

/**
 * 通用工具类
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2017年11月22日 下午2:43:47
 * CommonUtils.java
 */
public class CommonUtils {

    private CommonUtils() {
    }

    /**
     * 判断整数是否大于零
     *
     * @param number
     * @return
     */
    public static boolean isIntThanZero(int number) {
        boolean bool = false;
        if (number > 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * 新增,修改提示
     *
     * @param count
     * @return
     */
    public static Result msg(int count) {
        if (isIntThanZero(count)) {
            return Result.ofSuccessMsg(MsgConstant.MSG_OPERATION_SUCCESS);
        }
        return Result.ofFail(MsgConstant.MSG_OPERATION_FAILED);
    }


    /**
     * 查询详情提示
     *
     * @param data
     * @return
     */
    public static <R> Result<R> msg(R data) {
        if (isNullOrEmpty(data)) {
            return Result.ofFail(MsgConstant.MSG_INIT_FORM);
        }
        return Result.ofSuccess(data);
    }

    /**
     * 返回数据
     *
     * @param data
     * @return
     */
    public static <R> Result<R> msgNotNull(R data) {
        return Result.ofSuccess(data);
    }

    /**
     * 删除提示
     *
     * @param total
     * @param count
     * @return
     */
    public static Result msg(Object[] total, int count) {
        if (total.length == count) {
            return Result.ofSuccessMsg(MsgConstant.MSG_OPERATION_SUCCESS);
        } else {
            if (isIntThanZero(count)) {
                return Result.ofFail(MsgConstant.removeFailed(total.length, count));
            } else {
                return Result.ofFail(MsgConstant.MSG_OPERATION_FAILED);
            }
        }
    }


    public static boolean isNullOrEmpty(Object obj) {
        boolean bool = false;
        if (obj == null) {
            bool = true;
        }
        return bool;
    }
}
