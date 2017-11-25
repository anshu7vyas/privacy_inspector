package util;

import java.io.IOException;
import java.io.*;
import java.nio.ByteBuffer;

/**
 * Utility class that houses the DOUBLE EPSILON check function.
 *
 * @author Anshul Vyas
 */
public class EpsilonCheck {

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