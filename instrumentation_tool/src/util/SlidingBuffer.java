package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Custom sized Sliding Window Circular Buffer Implementation.
 *
 * @author Anshul Vyas
 */
public class SlidingBuffer {

    private int windowSize;
    private byte[] circularBuffer;
    private int index;
    private boolean filled;
    //private static SlidingBuffer slidingBuffer = new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE);

    /* A hashmap to keep record of different sliding buffers for different output streams */
    private static Map<Object, SlidingBuffer> internalMap = new HashMap<Object, SlidingBuffer>();

    /**
     * Constructor defining the window size required for the analysis
     *
     * @param windowSize
     */
    private SlidingBuffer(int windowSize) {
        this.windowSize = windowSize;
        circularBuffer = new byte[windowSize];
        index = 0;
    }

    /**
     * Creates an instance for the object key in the internalMap
     *
     * @param object
     * @return new instance of the Sliding Buffer according to the Object in the internalMap
     */
    public static SlidingBuffer getInstance(Object object) {
        if (!internalMap.containsKey(object)){
            internalMap.put(object, new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE));

        }
        return internalMap.get(object);
    }

    /**
     * Inserts a byte into the circular buffer
     *
     * @param b a single byte
     */
    public void add(byte b) {
        circularBuffer[index] = b;
        if(!filled && index == windowSize-1)
            filled = true;
        index = (index+1) % windowSize;     // move index in circular fashion
    }

    /**
     * Checks if the sliding buffer is filled completely
     *
     * @return True if the sliding buffer is filled, else false
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Gets different Sliding Buffers for different sizes defined to help in the analysis
     *
     * @param size
     * @return sliding buffer of size defined
     */
    public byte[] getCircularBuffer(int size) {
        if (!filled && size > index) {      // buffer not full yet
            return null;
        }
        if (size > windowSize) {            // error case
            return null;
        }
        byte[] temp = new byte[size];
        int tempIndex = size-1;
        int i = (index-1 < 0) ? windowSize-1 : index-1;

        while(tempIndex >= 0) {
            temp[tempIndex--] = circularBuffer[i];
            i = (i-1 < 0) ? windowSize-1 : i-1;
        }
        return temp;
    }
}