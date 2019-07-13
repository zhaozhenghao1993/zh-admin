package com.zhenghao.admin.monitor.vo;

import java.io.Serializable;

/**
 * 🙃
 * 🙃 访问量统计VO类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/26 10:55
 * VisitCountVO.java
 */

public class VisitCountVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总访问量
     */
    private Integer totalVisitCount;

    /**
     * 总访问量
     */
    private Integer todayVisitCount;

    public Integer getTotalVisitCount() {
        return totalVisitCount;
    }

    public void setTotalVisitCount(Integer totalVisitCount) {
        this.totalVisitCount = totalVisitCount;
    }

    public Integer getTodayVisitCount() {
        return todayVisitCount;
    }

    public void setTodayVisitCount(Integer todayVisitCount) {
        this.todayVisitCount = todayVisitCount;
    }
}
