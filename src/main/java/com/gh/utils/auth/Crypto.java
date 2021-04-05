package com.gh.utils.auth;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

@Slf4j
public class Crypto {

    public static String sha256(String string){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] saltBytes = new byte[string.getBytes("UTF-8").length
                    + string.getBytes("UTF-8").length];
            byte[] hash = digest.digest(saltBytes);
            StringBuffer hexString = new StringBuffer();

            for(int i=0;i<hash.length;i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
