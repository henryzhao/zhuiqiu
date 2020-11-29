package com.eamon.zhuiqiu.util.security;

/**
 * Created by Angel on 2020/11/29.
 */
public interface CodeMethod {
    enum SUPPORT {
        CODE_MD5, CODE_BASE64
    }

    String encode(byte[] content);

    byte[] decode(String content);

    String encode(String content);
}
