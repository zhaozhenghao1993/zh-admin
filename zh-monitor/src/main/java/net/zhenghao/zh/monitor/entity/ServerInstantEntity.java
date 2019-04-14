package net.zhenghao.zh.monitor.entity;

import net.zhenghao.zh.common.utils.DataHandleUtils;
import net.zhenghao.zh.common.utils.FileUtils;
import net.zhenghao.zh.monitor.entity.server.Cpu;
import net.zhenghao.zh.monitor.entity.server.Jvm;
import net.zhenghao.zh.monitor.entity.server.Mem;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.util.Util;

/**
 * 🙃
 * 🙃 服务器实时信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 17:26
 * ServerInstantEntity.java
 */

public class ServerInstantEntity {

    private static SystemInfo systemInfo = new SystemInfo();

    /**
     * Cpu相关信息
     */
    private Cpu cpu = new Cpu();

    /**
     * 内存相关信息
     */
    private Mem mem = new Mem();

    /**
     * JVM相关信息
     */
    private Jvm jvm = new Jvm();

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Mem getMem() {
        return mem;
    }

    public void setMem(Mem mem) {
        this.mem = mem;
    }

    public Jvm getJvm() {
        return jvm;
    }

    public void setJvm(Jvm jvm) {
        this.jvm = jvm;
    }

    public void init() {
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        setCpuInfo(hal.getProcessor());
        setMemInfo(hal.getMemory());
        setJvmInfo();
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor) {
        // CPU信息
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setUsedPercent((int)(processor.getSystemCpuLoad() * 100));
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory) {
        mem.setTotal(FileUtils.convertFileSize(memory.getTotal()));
        mem.setUsed(FileUtils.convertFileSize(memory.getTotal() - memory.getAvailable()));
        mem.setFree(FileUtils.convertFileSize(memory.getAvailable()));
        int percent = 0;
        try {
            percent = Integer.parseInt(DataHandleUtils.accuracy(memory.getTotal() - memory.getAvailable(), memory.getTotal(), 0));
        } catch (NumberFormatException e) {
        }
        mem.setUsedPercent(percent);
    }

    /**
     * 设置JVM信息
     */
    private void setJvmInfo() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long max = Runtime.getRuntime().maxMemory();
        jvm.setTotal(FileUtils.convertFileSize(total));
        jvm.setUsed(FileUtils.convertFileSize(total - free));
        jvm.setFree(FileUtils.convertFileSize(free));
        jvm.setMax(FileUtils.convertFileSize(max));
        int percent = 0;
        try {
            percent = Integer.parseInt(DataHandleUtils.accuracy(total - free, total, 0));
        } catch (NumberFormatException e) {
        }
        jvm.setUsedPercent(percent);
    }
}