package net.zhenghao.zh.monitor.entity.server;

import net.zhenghao.zh.common.utils.DateUtils;

/**
 * 🙃
 * 🙃 jvm信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 17:27
 * Jvm.java
 */

public class Jvm {

    /**
     * 当前JVM占用的内存总数(M)
     */
    private String total;

    /**
     * JVM最大可用内存总数(M)
     */
    private String max;

    /**
     * JVM空闲内存(M)
     */
    private String free;

    /**
     * JVM空闲内存(M)
     */
    private String used;

    /**
     * JVM空闲内存使用率(M)
     */
    private int usedPercent;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public int getUsedPercent() {
        return usedPercent;
    }

    public void setUsedPercent(int usedPercent) {
        this.usedPercent = usedPercent;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, DateUtils.getServerStartDate());
    }

    /**
     * JDK运行时间
     */
    public String getRunTime()
    {
        return DateUtils.getDatePoor(DateUtils.getNowDate(), DateUtils.getServerStartDate());
    }
}
