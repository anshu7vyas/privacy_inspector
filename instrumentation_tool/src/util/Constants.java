package util;

/**
 * Defines all the constants, including mock-up data being used throughout the other classes
 *
 * @author Anshul Vyas
 */
public class Constants {
    public final static int DOUBLE_BYTE_SIZE = 8;
    public final static int CONTACT_INFO_BYTE_SIZE = 10;

    /* mock-up geo location coordinates */
    public final static double ASPECT_LATITUDE = 34.98785;      //93281;      // Line of control - IND PAK border //27.98785;      //Mt. Everest
    public final static double ASPECT_LONGITUDE = 76.98785;           //76571;     // Line of control - IND PAK border //86.92503;      //Mt. Everest
//    public final static String ASPECT_LATITUDE_STRING = "34.93281";
//    public final static String ASPECT_LONGITUDE_STRING = "76.76571";

    /* mock-up IMEI */
    public final static String ASPECT_IMEI = "424242424242424"; // 15 digits

    /* mock-up Contact Details */
    public final static String ASPECT_CONTACT_NAME = "Kryptonite_Superhuman";
    public final static String ASPECT_CONTACT_FIRST_NAME = "Neanderthals";
    public final static String ASPECT_CONTACT_LAST_NAME = "Supersapiens";
    public final static String ASPECT_CONTACT_NUMBER = "742-742-4242";
    public final static String ASPECT_CONTACT_EMAIL = "neo@super.co";

    /* EPSILON = 0.00001 */
    public final static double DOUBLE_EPSILON = 0.00001;

    /* Maximum size of Sliding Buffer */
    public final static int SLIDING_WINDOW_SIZE = 15;

}
