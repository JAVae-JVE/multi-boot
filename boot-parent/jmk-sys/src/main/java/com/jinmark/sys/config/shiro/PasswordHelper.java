package com.jinmark.sys.config.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.jinmark.sys.domain.SysUser;



/**
 * 后台加密工具类
 * QC
 * 2017年4月25日 下午3:43:18
 */
public class PasswordHelper {

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String algorithmName = "md5";
    private final static int hashIterations = 2;
    /**
     * 保存用户时加密方法
     * @param user
     * @return void
     */
    public static void encryptPassword(SysUser user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
    /**
     * 验证密码时加密
     * @param user
     * @return String
     */
    public static String encryptPasswordValidation(SysUser user) {
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        return newPassword;
    }
}
