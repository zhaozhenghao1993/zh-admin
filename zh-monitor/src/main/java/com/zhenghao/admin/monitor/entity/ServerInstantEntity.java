package com.zhenghao.admin.monitor.entity;

import com.zhenghao.admin.common.util.DataHandleUtils;
import com.zhenghao.admin.common.util.FileUtils;
import com.zhenghao.admin.monitor.entity.server.Cpu;
import com.zhenghao.admin.monitor.entity.server.Jvm;
import com.zhenghao.admin.monitor.entity.server.Mem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

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
@Component
public class ServerInstantEntity {

    private static final Logger logger = LoggerFactory.getLogger(ServerInstantEntity.class);

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
        logger.info("ServerInstantEntity  1");
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        logger.info("ServerInstantEntity  2");
        setCpuInfo(hal.getProcessor());
        logger.info("ServerInstantEntity  3");
        setMemInfo(hal.getMemory());
        logger.info("ServerInstantEntity  4");
        setJvmInfo();
        logger.info("ServerInstantEntity  5");
    }

    /**
     * 设置CPU信息
     */
    private void setCpuInfo(CentralProcessor processor) {
        // CPU信息
        logger.info("ServerInstantEntity  6");
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        logger.info("ServerInstantEntity  7");
        cpu.setUsedPercent((int) (processor.getSystemCpuLoad() * 100));
        logger.info("ServerInstantEntity  8");
    }

    /**
     * 设置内存信息
     */
    private void setMemInfo(GlobalMemory memory) {
        logger.info("ServerInstantEntity  9");
        mem.setTotal(FileUtils.convertFileSize(memory.getTotal()));
        logger.info("ServerInstantEntity  10");
        mem.setUsed(FileUtils.convertFileSize(memory.getTotal() - memory.getAvailable()));
        logger.info("ServerInstantEntity  11");
        mem.setFree(FileUtils.convertFileSize(memory.getAvailable()));
        logger.info("ServerInstantEntity  12");
        int percent = 0;
        try {
            percent = Integer.parseInt(DataHandleUtils.accuracy((double) (memory.getTotal() - memory.getAvailable()), (double) memory.getTotal(), 0));
            logger.info("ServerInstantEntity  13");
        } catch (NumberFormatException e) {
            logger.error("percent number format exception", e);
        }
        mem.setUsedPercent(percent);
    }

    /**
     * 设置JVM信息
     */
    private void setJvmInfo() {
        logger.info("ServerInstantEntity  14");
        long total = Runtime.getRuntime().totalMemory();
        logger.info("ServerInstantEntity  15");
        long free = Runtime.getRuntime().freeMemory();
        logger.info("ServerInstantEntity  16");
        long max = Runtime.getRuntime().maxMemory();
        logger.info("ServerInstantEntity  17");
        jvm.setTotal(FileUtils.convertFileSize(total));
        jvm.setUsed(FileUtils.convertFileSize(total - free));
        jvm.setFree(FileUtils.convertFileSize(free));
        jvm.setMax(FileUtils.convertFileSize(max));
        logger.info("ServerInstantEntity  18");
        int percent = 0;
        try {
            logger.info("ServerInstantEntity  19");
            percent = Integer.parseInt(DataHandleUtils.accuracy((double) (total - free), (double) total, 0));
        } catch (NumberFormatException e) {
            logger.error("percent number format exception", e);
        }
        jvm.setUsedPercent(percent);
        logger.info("ServerInstantEntity  10");
    }
}
