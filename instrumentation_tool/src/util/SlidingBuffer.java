package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SlidingBuffer {

    private int windowSize;
    private byte[] circularBuffer;
    private int index;
    private boolean filled;
    //private static SlidingBuffer slidingBuffer = new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE);
    private static Map<Object, SlidingBuffer> internalMap = new HashMap<Object, SlidingBuffer>();

    private SlidingBuffer(int windowSize) {
        this.windowSize = windowSize;
        circularBuffer = new byte[windowSize];
        index = 0;
    }

    public static SlidingBuffer getInstance(Object object) {
        if (!internalMap.containsKey(object)){
            internalMap.put(object, new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE));

        }
        return internalMap.get(object);
    }

    public void add(byte b) {

        circularBuffer[index] = b;
        if(!filled && index == windowSize-1)
            filled = true;
        index = (index+1) % windowSize;     // move index in circular fashion
    }

    public boolean isFilled() {
        return filled;
    }


    public byte[] getCircularBuffer(int size) {
        if (!filled && size > index) { // buffer not full yet
            return null;
        }
        if (size > windowSize) {  // error case
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