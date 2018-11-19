package com.system.utils;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class MD5Utils {

    public static String getFileMD5(File file) throws IOException {
        return Files.hash(file, Hashing.md5()).toString();
    }


    public static String getStringMD5(String input) {
        return Hashing.md5().hashBytes(input.getBytes()).toString();
    }

    public static String getBytesMD5(byte[] bytes) {
        return Hashing.md5().hashBytes(bytes).toString();
    }

    public static byte[] getMD5Bytes(byte[] bytes) {
        return Hashing.md5().hashBytes(bytes).asBytes();
    }

    public static void main(String[] args) throws IOException {
        Date date = new Date();
        System.out.println(getFileMD5(new File("/Users/dasouche/Documents/tfbcode.zip")));
        System.out.println("耗时:" + (System.currentTimeMillis() - date.getTime()) + "毫秒");

        date = new Date();
        File file = new File("/Users/dasouche/Documents/tfbcode.zip");
        System.out.println(getBytesMD5(IOUtils.toByteArray(new FileInputStream(file))));
        System.out.println("耗时:" + (System.currentTimeMillis() - date.getTime()) + "毫秒");

    }
}
