package com.eamon.zhuiqiu.util.security;

/**
 * Created by Angel on 2020/11/29.
 */
public interface CipherMethod {

    enum SUPPORT {
        CIPHER_RSA, CIPHER_AES
    }


    byte[] encrypt(byte[] content, String key);

    byte[] decrypt(byte[] content, String key);
}
