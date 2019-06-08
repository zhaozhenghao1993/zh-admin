package net.zhenghao.zh.monitor.entity;

import net.zhenghao.zh.common.config.UploadConfig;
import net.zhenghao.zh.common.utils.DataHandleUtils;
import net.zhenghao.zh.common.utils.FileUtils;
import net.zhenghao.zh.common.utils.IPUtils;
import net.zhenghao.zh.monitor.entity.server.App;
import net.zhenghao.zh.monitor.entity.server.Jvm;
import net.zhenghao.zh.monitor.entity.server.Sys;
import net.zhenghao.zh.monitor.entity.server.SysDisk;
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
        Properties props = System.getProperties();
        setJvmInfo(props);
        setSysInfo(props);
        setAppInfo(props);
        setSysFiles(systemInfo.getOperatingSystem());
    }

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo(Properties props) {
        jvm.setName(props.getProperty("java.vm.name"));
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo(Properties props) {
        sys.setServerName(IPUtils.getHostName());
        sys.setServerIp(IPUtils.getHostIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
    }

    /**
     * 设置App应用信息
     */
    private void setAppInfo(Properties props) {
        app.setAppDir(props.getProperty("user.dir"));
        app.setUploadDir(uploadConfig.getFolder());
        app.setLogDir(logFolder);
    }

    /**
     * 设置磁盘信息
     */
    private void setSysFiles(OperatingSystem os) {
        sysDisks.clear();
        FileSystem fileSystem = os.getFileSystem();
        OSFileStore[] fsArray = fileSystem.getFileStores();
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
            try {
                percent = Integer.parseInt(DataHandleUtils.accuracy(used, total, 0));
            } catch (NumberFormatException e) {
            }
            sysDisk.setUsedPercent(percent + "%");
            sysDisks.add(sysDisk);
        }
    }
}
