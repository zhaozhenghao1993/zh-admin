package net.zhenghao.zh.common.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.zhenghao.zh.common.constant.SystemConstant;
import org.joda.time.DateTime;

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

    private static RsaKeyHelper rsaKeyHelper = new RsaKeyHelper();

    /**
     * 密钥加密token
     *
     * @param jwtInfo
     * @param priKeyPath
     * @param expire
     * @return
     * @throws Exception
     */
    public static String generateToken(JWTInfo jwtInfo, String priKeyPath, int expire) throws Exception {
        String compactJws = Jwts.builder()
                .setSubject(jwtInfo.getUsername())
                .claim(SystemConstant.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .claim(SystemConstant.JWT_KEY_USERNAME, jwtInfo.getUsername())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.RS256, rsaKeyHelper.getPrivateKey(priKeyPath))
                .compact();
        return compactJws;
    }
}
