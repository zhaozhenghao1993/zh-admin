package com.zhenghao.admin.common.annotation;

import com.zhenghao.admin.common.constant.SystemConstant;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @SysLog("里面写内容，必须赋值到value属性中")
 *
 * author:zhaozhenghao
 * Email :736720794@qq.com
 * date  :2017年11月20日
 * SysLog.java
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String value() default "";

    SystemConstant.LogType type() default SystemConstant.LogType.OPERATION;

}
/**
 * @Target说明了Annotation所修饰的对象范围： Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）
 * 、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。
 * 在Annotation类型的声明中使用了target可更加明晰其修饰的目标。
 * 作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 * 取值(ElementType)有：
 * <p>
 * 　　　　1.CONSTRUCTOR:用于描述构造器
 * 　　　　2.FIELD:用于描述域
 * 　　　　3.LOCAL_VARIABLE:用于描述局部变量
 * 　　　　4.METHOD:用于描述方法
 * 　　　　5.PACKAGE:用于描述包
 * 　　　　6.PARAMETER:用于描述参数
 * 　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
 * @Retention定义了该Annotation被保留的时间长短：某些Annotation仅出现在源代码中， 而被编译器丢弃；而另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽略，
 * 而另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）。
 * 使用这个meta-Annotation可以对 Annotation的“生命周期”限制。
 * <p>
 * 作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * 　取值（RetentionPoicy）有：
 * <p>
 * 　　　　1.SOURCE:在源文件中有效（即源文件保留）
 * 　　　　2.CLASS:在class文件中有效（即class保留）
 * 　　　　3.RUNTIME:在运行时有效（即运行时保留）
 * <p>
 * <p>
 * 　@Documented用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，
 * 因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 */
