package com.zhenghao.admin.main.auth.controller;

import com.zhenghao.admin.auth.entity.SysUserEntity;
import com.zhenghao.admin.auth.service.SysUserService;
import com.zhenghao.admin.common.entity.Result;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.when;

/**
 * 🙃
 * 🙃测试Springboot应用需要依赖一个非常重要的注解@SpringBootTest，
 * 这个注解会为测试用例构建Spring容器。@SpringBootTest注解修饰的测试用例默认不会启动web容器，
 * 如果需要启动web容器需要设置webEnvironment属性：
 *
 * MOCK(默认)：会启动一个mock的web server，可以配合@AutoConfigureMockMvc注解对web应用进行测试(后面会举例)
 * RANDOM_PORT：创建ApplicationContext上下文，启动一个真实的Web容器，监听一个随机的端口。
 * DEFINED_PORT：创建ApplicationContext上下文，启动一个真实的Web容器，监听SpringBoot配置配置文件中指定的端口，默认是8080端口。
 * NONE：只是启动ApplicationContext，不会启动任何(Mock或者非Mock)web容器。
 * 如果是使用Junit来进行单元测试，再增加一个@RunWith(SpringRunner.class)或者@RunWith(SpringJUnit4ClassRunner.class)注解。
 * 🙃
 *
 * //在所有测试方法前执行一次，一般在其中写上整体初始化的代码
 * @BeforeClass
 *
 * //在所有测试方法后执行一次，一般在其中写上销毁和释放资源的代码
 * @AfterClass
 *
 * //在每个测试方法前执行，一般用来初始化方法（比如我们在测试别的方法时，类中与其他测试方法共享的值已经被改变，为了保证测试结果的有效性，我们会在@Before注解的方法中重置数据）
 * @Before
 *
 * //在每个测试方法后执行，在方法执行完成后要做的事情
 * @After
 *
 * // 测试方法执行超过1000毫秒后算超时，测试将失败
 * @Test(timeout = 1000)
 *
 * // 测试方法期望得到的异常类，如果方法执行没有抛出指定的异常，则测试失败
 * @Test(expected = Exception.class)
 *
 * // 执行测试时将忽略掉此方法，如果用于修饰类，则忽略整个类
 * @Ignore(“not ready yet”)
 *
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/10/16 22:21
 * TestControllerTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TestControllerTest {

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void test() {
        SysUserService sysUserService = Mockito.mock(SysUserService.class);
        Result<SysUserEntity> result = Result.ofSuccess(new SysUserEntity());
        when(sysUserService.getUserById(1L)).thenReturn(result);
        Result<SysUserEntity> resultMock = sysUserService.getUserById(1L);
        System.out.println(resultMock.toString());
        System.out.println("test");
    }
}
