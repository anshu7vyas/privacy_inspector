package aspectj;

import java.io.IOException;
import java.io.*;

/**
 * Created by av7 on 4/24/17.
 */
public class ByteForByte {

    public static int compareBytes(byte[] source, byte[] match, int index, int size) {
        // sanity checks
        if (source == null || match == null)
            return -1;
        if (source.length == 0 || match.length == 0)
            return -1;
        int ret = -1;
        int spos = (index + 1) % size;
        int mpos = 0;
        byte m = match[mpos];
        for (; spos != index; spos = (spos + 1) % size) {
            if (m == source[spos]) {
                // starting match
                if (mpos == 0)
                    ret = spos;
                    // finishing match
                else if (mpos == match.length - 1)
                    return ret;
                mpos++;
                m = match[mpos];
            } else {
                ret = -1;
                mpos = 0;
                m = match[mpos];
            }
        }
        return ret;
    }

    public static byte[] doubleToByteArray(double value) {
        return String.valueOf(value).getBytes();
    }

    public static byte[] intToByteArray(int x) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    DataOutputStream out = new DataOutputStream(bos);
    out.writeInt(x);
    out.close();
    byte[] intBytes = bos.toByteArray();
    bos.close();
    return intBytes;
}

    /*public static void main(String[] args) throws IOException {
        String arr1 = "91.1 and 91.1 and 915584987";
        byte[] barr1 = arr1.getBytes();
        System.out.write(barr1);
        System.out.println();


        byte[] a = toByteArray(-91.1);
        byte[] b = toByteArray(-91.1);
        byte[] c = "91558.89".getBytes();
        System.out.println(find(barr1, c));
        System.out.println(find(a,b));
    }*/
}
