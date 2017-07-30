package util;

import java.io.IOException;
import java.io.*;
import java.nio.ByteBuffer;


public class ByteConversion {

    /**
     * byte[] -> double
     */
    public static double bytes2Double(byte[] value) {
        return ByteBuffer.wrap(value).getDouble();
    }

    /**
     * double -> byte[]
     */
    public static byte[] double2Bytes(double value) {
        byte[] bytes = new byte[Constants.DOUBLE_BYTE_SIZE];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    /**
     * byte[] -> string
     */
    public static String byte2String(byte[] value) {
        String temp = new String(value);
        return temp;
    }

    /** Probably encoding will matter
     * string -> byte[]
     */
    public static byte[] str2Bytes(String value) {
        return value.getBytes();
    }

    /**
     * int -> byte[]
     */
    public static byte[] int2Bytes(int x) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bos);
        out.writeInt(x);
        out.close();
        byte[] intBytes = bos.toByteArray();
        bos.close();
        return intBytes;
    }

    /**
     * double -> String -> byte[]
     */
    public static byte[] double2Str2Bytes(double value) {
        return String.valueOf(value).getBytes();
    }
}