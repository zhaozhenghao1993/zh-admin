package net.zhenghao.zh.monitor.entity;

import net.zhenghao.zh.monitor.entity.server.App;
import net.zhenghao.zh.monitor.entity.server.Jvm;
import net.zhenghao.zh.monitor.entity.server.Sys;
import net.zhenghao.zh.monitor.entity.server.SysDisk;

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

public class ServerBaseEntity {

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

    /**
     * 设置Java虚拟机
     */
    private void setJvmInfo() {
        Properties props = System.getProperties();
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
    }

    /**
     * 设置服务器信息
     */
    private void setSysInfo()
    {
        Properties props = System.getProperties();
        /*sys.setComputerName(IpUtils.getHostName());
        sys.setComputerIp(IpUtils.getHostIp());
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));*/
    }
}
