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

        if (!filled && index == (windowSize - 1)) {
            filled = true;
            index = (index + 1) % windowSize;                               //move index in circular way
        } else {
            index++;
        }
    }

    public boolean isFilled() {
        return filled;
    }

    public byte[] getCircularBuffer() {
        return circularBuffer;
    }
}
