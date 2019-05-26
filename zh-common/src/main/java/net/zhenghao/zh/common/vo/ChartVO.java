package net.zhenghao.zh.common.vo;

import java.io.Serializable;

/**
 * 🙃
 * 🙃 图表统计VO类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/05/25 22:17
 * ChartVO.java
 */

public class ChartVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String x;

    /**
     * y轴 类型不符合请自行构建
     */
    private Integer y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}
