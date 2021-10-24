package com.zhenghao.admin.common.jwt;

import com.zhenghao.admin.common.constant.SystemConstants;
import com.zhenghao.admin.common.util.StringCommonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 🙃
 * 🙃 JWT 辅助类
 * 🙃
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019/01/14 22:36
 * JWTHelper.java
 */

public class JWTHelper {

    private JWTHelper() {
    }

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, PrivateKey privateKey, int expire) {
        return Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(SystemConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(SystemConstants.JWT_KEY_NAME, jwtInfo.getName())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static JWTInfo getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new JWTInfo(
                StringCommonUtils.getObjectValue(body.get(SystemConstants.JWT_KEY_USER_ID)),
                body.getSubject(),
                StringCommonUtils.getObjectValue(body.get(SystemConstants.JWT_KEY_NAME)
                ));
    }
}
