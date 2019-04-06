package net.zhenghao.zh.monitor.entity.server;

/**
 * 🙃
 * 🙃 cpu信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 17:27
 * Cpu.java
 */

public class Cpu {

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private int totalPercent;

    /**
     * CPU系统使用率
     */
    private int sysPercent;

    /**
     * CPU用户使用率
     */
    private int usedPercent;

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public int getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(int totalPercent) {
        this.totalPercent = totalPercent;
    }

    public int getSysPercent() {
        return sysPercent;
    }

    public void setSysPercent(int sysPercent) {
        this.sysPercent = sysPercent;
    }

    public int getUsedPercent() {
        return usedPercent;
    }

    public void setUsedPercent(int usedPercent) {
        this.usedPercent = usedPercent;
    }
}
