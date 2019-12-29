package com.zhenghao.admin.main.common.jwt;

import com.zhenghao.admin.common.jwt.JWTHelper;
import com.zhenghao.admin.common.jwt.JWTInfo;
import com.zhenghao.admin.common.jwt.RsaKeyManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JWTHelperTest {

    @Value("${zh-admin.jwt.expire}")
    private int expire;

    @Autowired
    private RsaKeyManager rsaKeyManager;

    @Test
    public void token() {
        JWTInfo jwtInfo = new JWTInfo(1L, "admin", "Administrator");
        String token = JWTHelper.generateToken(jwtInfo, rsaKeyManager.getPrivateKey(), expire);
        Assert.assertNotNull(token);

        JWTInfo jwtInfoNew = JWTHelper.getInfoFromToken(token, rsaKeyManager.getPublicKey());
        Assert.assertEquals(jwtInfo.getUserId(), jwtInfoNew.getUserId());
        Assert.assertEquals(jwtInfo.getUsername(), jwtInfoNew.getUsername());
        Assert.assertEquals(jwtInfo.getName(), jwtInfoNew.getName());
    }
}