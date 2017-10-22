package util;

import java.io.IOException;
import java.io.*;
import java.nio.ByteBuffer;

/**
 * Utility class for various conversions, also houses the DOUBLE EPSILON check function.
 *
 * @author Anshul Vyas
 */
public class ByteConversion {

    /* byte[] -> double */
    public static double bytes2Double(byte[] value) {
        return ByteBuffer.wrap(value).getDouble();
    }


    /* double -> byte[] */
    public static byte[] double2Bytes(double value) {
        byte[] bytes = new byte[Constants.DOUBLE_BYTE_SIZE];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    /* byte[] -> String */
    public static String byte2String(byte[] value) {
        String temp = new String(value);
        return temp;
    }

    /* String -> byte[] */
    public static byte[] string2Bytes(String value) {
        return value.getBytes();
    }

    /* int -> byte[] */
    public static byte[] int2Bytes(int x) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(bos);
        out.writeInt(x);
        out.close();
        byte[] intBytes = bos.toByteArray();
        bos.close();
        return intBytes;
    }

    /* double -> String -> byte[] */
    public static byte[] double2String2Bytes(double value) {
        return String.valueOf(value).getBytes();
    }

    /**
     * Compares and checks for equality between two double variables
     *
     * @param a
     * @param b
     * @return true if in equal range, i.e, d - E <= d <= d + E , else false
     */
    public static boolean almostEqualDouble(double a, double b) {
        if (a == b) return true;
        return Math.abs(a - b) <= Constants.DOUBLE_EPSILON;
    }
}