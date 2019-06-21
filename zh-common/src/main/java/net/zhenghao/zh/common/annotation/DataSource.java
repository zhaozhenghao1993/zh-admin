package net.zhenghao.zh.common.annotation;

import java.lang.annotation.*;

/**
 * 数据源注解
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2018年3月1日 下午3:10:33
 * DataSource.java
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {

    String value() default "";

}
