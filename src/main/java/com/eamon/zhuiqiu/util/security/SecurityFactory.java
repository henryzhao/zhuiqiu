package com.eamon.zhuiqiu.util.security;

import com.eamon.zhuiqiu.util.security.Impl.*;

/**
 * Created by Angel on 2020/11/29.
 */
public class SecurityFactory {
    public static CipherMethod getCipherMethod(CipherMethod.SUPPORT method) {

        if (method.equals(CipherMethod.SUPPORT.CIPHER_RSA)) {
            return RSA.getInstance();
        } else if (method.equals(CipherMethod.SUPPORT.CIPHER_AES)) {
            return AES.getInstance();
        } else if (method.equals("")) {

        }
        return AES.getInstance();
    }

    public static CodeMethod getCodeMethod(CodeMethod.SUPPORT method) {
        if (method.equals(CodeMethod.SUPPORT.CODE_BASE64)) {
            return Base64.getInstance();
        } else if (method.equals(CodeMethod.SUPPORT.CODE_MD5)) {
            return MD5.getInstance();
        }
        return MD5.getInstance();
    }
}
