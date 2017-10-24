package visitor;

import util.ByteConversion;
import util.Constants;
import util.EnsureEncoding;
import util.Logging;

/**
 * Visitor class - Implementation for all the analysis run on the Output Stream to inspect the mock-up data.
 *
 * @author Anshul Vyas
 */
public class SecurityChecker implements Visitor {

    private static SecurityChecker securityChecker = null;

    /* Instance of the Logging class */
    Logging logger = Logging.getInstance();

    private SecurityChecker() { }

    /**
     * @return new instance of the SecurityChecker
     */
    public static SecurityChecker getInstance() {
        if (securityChecker == null) {
            securityChecker = new SecurityChecker();
        }

        return securityChecker;
    }

    /**
     * Overrides the visit() method to run the analysis for IMEI number
     *
     * @param imeiObserver
     */
    @Override
    public void visit(IMEIObserver imeiObserver) {
        byte[] dumpBytes = imeiObserver.getIMEIBuffer();

        if(dumpBytes != null) {
            String imeiDecoded = EnsureEncoding.decode(dumpBytes);                  // detect encoding
            if (imeiDecoded.equals(Constants.ASPECT_IMEI)) {
                logger.printLog("\n\nERROR 2 :- Violating security policy. IMEI number has been detected in HTTP Stream.", dumpBytes);
            }
        }
    }

    /**
     * Overrides the visit() method to run the analysis for contact information
     *
     * @param contactObserver
     */
    @Override
    public void visit(ContactObserver contactObserver) {
        byte[] dumpBytes = contactObserver.getContactBuffer();

        if(dumpBytes != null) {
            String contactInformation = EnsureEncoding.decode(dumpBytes);           // detect encoding
           if (contactInformation.equals(Constants.ASPECT_CONTACT_NAME)) {// || contactInformation.equals(Constants.ASPECT_CONTACT_NUMBER) || contactInformation.equals(Constants.ASPECT_CONTACT_EMAIL)) {
               logger.printLog("\n\nERROR 3 :- Violating security policy. Contact Information has been detected in HTTP Stream.", dumpBytes);
           }
        }
    }

    /**
     * Overrides the visit() method to run the analysis for Geolocation coordinates
     *
     * @param locationObserver
     */
    @Override
    public void visit(LocationObserver locationObserver) {
        byte[] dumpBytes = locationObserver.getLocationBuffer();

        if (dumpBytes != null) {
            String coordinatesDecoded = EnsureEncoding.decode(dumpBytes);          // detect encoding
            try {
                Double coordinates = Double.valueOf(coordinatesDecoded);           // check if the characters can be converted to Double or not
                if (ByteConversion.almostEqualDouble(coordinates, Constants.ASPECT_LATITUDE)) {
                    logger.printLog("\n\nERROR 1.0 :- Violating security policy. Latitude coordinates has been detected in the HTTP Stream.", dumpBytes);
                }
                if (ByteConversion.almostEqualDouble(coordinates, Constants.ASPECT_LONGITUDE)) {
                    logger.printLog("\n\nERROR 1.1 :- Violating security policy. Longitude coordinates has been detected in the HTTP Stream.", dumpBytes);
                }
            } catch (NumberFormatException e) {
        
            }
        }
    }
}


