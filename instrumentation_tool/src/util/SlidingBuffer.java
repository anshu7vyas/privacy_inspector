package util;

/**
 * Custom sized Sliding Window Circular Buffer Implementation.
 *
 * @author Anshul Vyas
 */
public class SlidingBuffer{

    private int windowSize;
    private byte[] circularBuffer;
    private int index;
    private boolean filled;
    private static SlidingBuffer slidingBuffer;

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
     * Ensures single instance for SlidingBuffer
     *
     * @return instance of the Sliding Buffer
     */
    public static SlidingBuffer getInstance() {
        if (slidingBuffer == null) {
            slidingBuffer = new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE);
        }

        return slidingBuffer;
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
     * @return True if the sliding buffer is filled, else false
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * Fills different Sliding Buffers for different sizes defined to help in the analysis
     */
    public void fillBuffer(byte[] buffer) {
        if (buffer.length <= windowSize) {
            int tempIndex = buffer.length - 1;
            int i = (index-1 < 0) ? windowSize-1 : index -1;

            while(tempIndex >= 0) {
                buffer[tempIndex--] = circularBuffer[i];
                i = (i-1 < 0) ? windowSize-1 : i-1;
            }
        }
    }
}