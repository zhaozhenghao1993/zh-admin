package com.zhenghao.admin.monitor.entity;

import com.zhenghao.admin.common.config.UploadConfig;
import com.zhenghao.admin.common.util.DataHandleUtils;
import com.zhenghao.admin.common.util.FileUtils;
import com.zhenghao.admin.common.util.IPUtils;
import com.zhenghao.admin.monitor.entity.server.App;
import com.zhenghao.admin.monitor.entity.server.Jvm;
import com.zhenghao.admin.monitor.entity.server.Sys;
import com.zhenghao.admin.monitor.entity.server.SysDisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 🙃
 * 🙃 服务器基础信息
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/04/06 17:25
 * ServerBaseEntity.java
 */
@Component
public class ServerBaseEntity {

    private static final Logger logger = LoggerFactory.getLogger(ServerBaseEntity.class);

    @Value("${zh-admin.file.log.folder}")
    private String logFolder;

    @Autowired
    private UploadConfig uploadConfig;

    private static SystemInfo systemInfo = new SystemInfo();

    /**
     * JVM相关信息
     */
    private App app = new App();

    /**
     * JVM相关信息
     */
    private Jvm jvm = new Jvm();

    /**
     * 服务器相关信息
     */
    private Sys sys = new Sys();

    /**
     * 磁盘相关信息
     */
    private List<SysDisk> sysDisks = new LinkedList<>();

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Jvm getJvm() {
        return jvm;
    }

    public void setJvm(Jvm jvm) {
        this.jvm = jvm;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<SysDisk> getSysDisks() {
        return sysDisks;
    }

    public void setSysDisks(List<SysDisk> sysDisks) {
        this.sysDisks = sysDisks;
    }

    public void init() {
        logger.info("ServerBaseEntity 1 ");
        Properties props = System.getProperties();
        logger.info("ServerBaseEntity 2 ");
        setJvmInfo(props);
        setSysInfo(props);
        setAppInfo(props);
        logger.info("ServerBaseEntity 3 ");
        setSysFiles(systemInfo.getOperatingSystem());
        logger.info("ServerBaseEntity 4 ");
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo(Properties props) {
        logger.info("ServerBaseEntity 5 ");
        jvm.setName(props.getProperty("java.vm.name"));
        logger.info("ServerBaseEntity 6 ");
        jvm.setVersion(props.getProperty("java.version"));
        logger.info("ServerBaseEntity 7 ");
        jvm.setHome(props.getProperty("java.home"));
        logger.info("ServerBaseEntity 8 ");
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo(Properties props) {
        logger.info("ServerBaseEntity 9 ");
        sys.setServerName(IPUtils.getHostName());
        logger.info("ServerBaseEntity 10 ");
        sys.setServerIp(IPUtils.getHostIp());
        logger.info("ServerBaseEntity 11 ");
        sys.setOsName(props.getProperty("os.name"));
        logger.info("ServerBaseEntity 12 ");
        sys.setOsArch(props.getProperty("os.arch"));
        logger.info("ServerBaseEntity 13 ");
    }

    /**
     * 设置App应用信息
     */
    private void setAppInfo(Properties props) {
        logger.info("ServerBaseEntity 14 ");
        app.setAppDir(props.getProperty("user.dir"));
        logger.info("ServerBaseEntity 15 ");
        app.setUploadDir(uploadConfig.getFolder());
        app.setLogDir(logFolder);
        logger.info("ServerBaseEntity 16 ");
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os) {
        logger.info("ServerBaseEntity 17 ");
        sysDisks.clear();
        FileSystem fileSystem = os.getFileSystem();
        logger.info("ServerBaseEntity 18 ");
        OSFileStore[] fsArray = fileSystem.getFileStores();
        logger.info("ServerBaseEntity 19 ");
        for (OSFileStore fs : fsArray) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            SysDisk sysDisk = new SysDisk();
            sysDisk.setDirName(fs.getMount());
            sysDisk.setDiskType(fs.getType());
            sysDisk.setFileSystem(fs.getName());
            sysDisk.setTotal(FileUtils.convertFileSize(total));
            sysDisk.setFree(FileUtils.convertFileSize(free));
            sysDisk.setUsed(FileUtils.convertFileSize(used));
            int percent = 0;
            logger.info("ServerBaseEntity 20 ");
            try {
                percent = Integer.parseInt(DataHandleUtils.accuracy(used, total, 0));
            } catch (NumberFormatException e) {
                logger.error("percent number format exception", e);
            }
            sysDisk.setUsedPercent(percent + "%");
            sysDisks.add(sysDisk);
            logger.info("ServerBaseEntity 21 ");
        }
    }
}
