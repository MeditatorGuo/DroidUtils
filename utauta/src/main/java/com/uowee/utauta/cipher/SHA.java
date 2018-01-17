package com.uowee.utauta.cipher;

import java.security.MessageDigest;


public class SHA {
    public static byte[] encrypt(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(CipherType.SHA.getType());
        sha.update(data);
        return sha.digest();
    }
}
