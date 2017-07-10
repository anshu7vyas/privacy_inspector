package aspectj;

/**
 *
 */
public class SlidingBuffer {

    private int windowSize;
    private byte[] circularBuffer;
    private int index;
    private boolean filled;

    public SlidingBuffer(int windowSize) {
        this.windowSize = windowSize;
        circularBuffer = new byte[windowSize];
        index = 0;
    }

    public void add(byte b) {
        circularBuffer[index] = b;
        if(!filled && index == windowSize-1)
            filled = true;
        index = (index+1) % windowSize;                                 // move index in circular fashion
    }

    public boolean isFilled() {
        return filled;
    }

    public byte[] getCircularBuffer() {
        if (!filled) {
            return null;
        }
        byte[] temp = new byte[windowSize];
        int tempIndex = 0;
        int i = index;
        while(i != (index - 1 + windowSize) % windowSize) {
            temp[tempIndex++] = circularBuffer[i];
            i = (i + 1) % windowSize;
        }
        temp[tempIndex] = circularBuffer[i];
        return temp;
    }
}
