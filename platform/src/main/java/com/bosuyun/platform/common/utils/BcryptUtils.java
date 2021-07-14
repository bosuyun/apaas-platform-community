package com.bosuyun.platform.common.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * Created by liuyuancheng on 2020/12/18  <br/>
 */
public class BcryptUtils {
    public static void main(String[] args) {
        String password = "1234";
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
// $2a$12$US00g/uMhoSBm.HiuieBjeMtoN69SN.GE25fCpldebzkryUyopws6

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
// result.verified == true
        System.out.println(result);
    }
}
