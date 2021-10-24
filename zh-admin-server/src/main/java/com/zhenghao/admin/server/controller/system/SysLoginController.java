package com.zhenghao.admin.server.controller.system;

import com.google.code.kaptcha.Producer;
import com.zhenghao.admin.common.constant.HttpStatusConstants;
import com.zhenghao.admin.common.entity.Result;
import com.zhenghao.admin.common.enums.StatusTypeEnum;
import com.zhenghao.admin.common.util.MD5Utils;
import com.zhenghao.admin.common.util.RSAUtils;
import com.zhenghao.admin.server.entity.SysUserEntity;
import com.zhenghao.admin.server.entity.dto.SysLoginDTO;
import com.zhenghao.admin.server.entity.vo.SysLoginVO;
import com.zhenghao.admin.server.service.SysUserService;
import com.zhenghao.admin.server.util.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import static com.zhenghao.admin.common.constant.SystemConstants.API_PREFIX;

/**
 * 用户controller
 *
 * @author:zhaozhenghao
 * @Email :736720794@qq.com
 * @date :2019年2月16日 下午3:21:53
 * SysLoginController.java
 */
@RestController
@RequestMapping(API_PREFIX + "/sys")
public class SysLoginController {

    private final static Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    private final Producer producer;

    private final SysUserService sysUserService;

    @Autowired
    public SysLoginController(Producer producer, SysUserService sysUserService) {
        this.producer = producer;
        this.sysUserService = sysUserService;
    }

    /**
     * 验证码
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();

        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到 session
        SessionUtils.setCaptchaFromSession(request, text);
        //将图片写入页面
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @PostMapping("/login")
    public Result<SysLoginVO> login(@RequestBody SysLoginDTO sysLogin, HttpServletRequest request) {
        String username = sysLogin.getUsername();
        String password = sysLogin.getPassword();
        String captcha = sysLogin.getCaptcha();
        String captchaFromSession = SessionUtils.getCaptchaFromSession(request);
        SessionUtils.removeCaptchaFromSession(request);
        if (StringUtils.isBlank(captchaFromSession) || !captchaFromSession.equalsIgnoreCase(captcha)) {
            return Result.ofFail("验证码不正确");
        }

        RSAUtils.RsaKey rsaKey = SessionUtils.getRsaKeyFromSession(request);
        if (rsaKey == null) {
            return Result.ofFail("登陆超时，请重新登录!");
        }
        try {
            password = RSAUtils.decrypt(password, rsaKey.getPrivateKey());
        } catch (Exception e) {
            logger.error("rsa decrypt exception", e);
            return Result.ofFail("登陆超时，请重新登录!");
        }

        password = MD5Utils.encrypt(username, password);
        return validate(request, username, password);
    }

    private Result<SysLoginVO> validate(HttpServletRequest request, String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Result.ofFail("账号或密码不能为空!");
        }
        // 查询用户信息
        SysUserEntity user = sysUserService.getUserByName(username);

        // 账号不存在
        if (user == null) {
            return Result.ofFail("账号不存在!");
        }
        if (!password.equals(user.getPassword())) {
            return Result.ofFail("账号或密码错误!");
        }
        //账号锁定
        if (user.getStatus() == StatusTypeEnum.DISABLE.getValue()) {
            return Result.ofFail("账号已被锁定!");
        }

        SysUserEntity sysUserFromSession = new SysUserEntity();
        sysUserFromSession.setId(user.getId());
        sysUserFromSession.setUsername(user.getUsername());
        SessionUtils.setSysUserFromSession(request, sysUserFromSession);
        return Result.ofSuccess(new SysLoginVO(request.getSession().getId()));
    }

    @PostMapping("logout")
    public Result<Void> logout(HttpServletRequest request) {
        SessionUtils.removeSysUserFromSession(request);
        return Result.ofSuccessMsg("success logout");
    }

    @GetMapping("/rsa/key")
    public Result<String> getRsaKey(HttpServletRequest request) throws NoSuchAlgorithmException {
        RSAUtils.RsaKey rsaKey = RSAUtils.genKeyPair();
        SessionUtils.setRsaKeyFromSession(request, rsaKey);
        return Result.ofSuccess(rsaKey.getPublicKey());
    }
}
