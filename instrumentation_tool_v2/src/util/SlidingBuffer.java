package util;

import java.io.IOException;
import java.util.Arrays;

public class SlidingBuffer {

    private int windowSize;
    private byte[] circularBuffer;
    private int index;
    private boolean filled;
    private static SlidingBuffer slidingBuffer = new SlidingBuffer(Constants.SLIDING_WINDOW_SIZE);

    private SlidingBuffer(int windowSize) {
        this.windowSize = windowSize;
        circularBuffer = new byte[windowSize];
        index = 0;
    }

    public static SlidingBuffer getInstance() {
        return slidingBuffer;
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



    public static void main(String[] args) throws IOException {
       /* SlidingBuffer imeiBuffer = new SlidingBuffer(15);       //initialize in onCreate() method of the app
        String viewEncoding;

        String stringDump = "91.1 and -101.42 and 424242424242424";         //output stream data from write()
        String mockupIMEI = "424242424242424";                              //mockup data IMEI

        byte[] streamDump8 = stringDump.getBytes(StandardCharsets.UTF_8);      //convert to UTF-8 encoding
        System.out.println("stream dump8 = " + Arrays.toString(streamDump8));
        byte[] streamDump16 = stringDump.getBytes("UTF-16BE");  //convert to UTF-16BE encoding
        //System.out.println("stream dump16 = " + Arrays.toString(streamDump16));

        for (int i = 0; i < streamDump8.length; i++) {
            imeiBuffer.add(streamDump8[i]);
            //System.out.println("imeiBuffer = " + imeiBuffer);
            if (imeiBuffer.isFilled()) {
                byte[] imeiDumpBytes = imeiBuffer.getCircularBuffer();                       //15 digits filled in the circular buffer

                viewEncoding = StringEncodingDetector.suggestEncoding(imeiDumpBytes);          //detect encoding
                //System.out.println("found encoding = " + viewEncoding);
                //System.out.println("detected encoding = " + viewEncoding);
                if (viewEncoding == "UTF-8") {
                    byte[] mockupIMEIbytes = mockupIMEI.getBytes(viewEncoding);

                    String convertedDump = Conversions.byte2String(imeiDumpBytes);
                    //System.out.println("converted byte2string dump = " + convertedDump);
                    String mockupConversion = Conversions.byte2String(mockupIMEIbytes);
                    //System.out.println("converted mockupImei byte2string = " + mockupConversion);

                    if (convertedDump.equals(mockupConversion)) {
                        System.out.println("Warning - same IMEI data found.. " + convertedDump);
                    }
                }

            }
        }*/
        SlidingBuffer buffer = new SlidingBuffer(8);

        for(byte i=0; i < 20; i++) {
            buffer.add(i);
            byte[] res = buffer.getCircularBuffer(4);
            print(res);
        }

    }

    private static void print(byte[] buffer) {
        if(buffer == null) {
            System.out.println("Null");
        } else {
            System.out.println(Arrays.toString(buffer));
        }
    }
}