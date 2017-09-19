package visitor;

import util.ByteConversion;
import util.Constants;
import util.EnsureEncoding;
import util.Logging;

/**
 * Created by av7 on 7/29/17.
 */
public class SecurityChecker implements Visitor {
    private static SecurityChecker securityChecker = null;
    Logging logger = Logging.getInstance();

    private SecurityChecker() { }

    public static SecurityChecker getInstance() {
        if (securityChecker == null) {
            securityChecker = new SecurityChecker();
        }

        return securityChecker;
    }

    public void visit(IMEIObserver imeiObserver) {
        byte[] dumpBytes = imeiObserver.getIMEIBuffer();
        if(dumpBytes != null) {
            String imeiDecoded = EnsureEncoding.decode(dumpBytes);          //detect encoding
            if (imeiDecoded.equals(Constants.ASPECT_IMEI)) {
                logger.printLog("\n\nERROR 2 :- Violating security policy. IMEI number has been detected in HTTP Stream.", dumpBytes);
            }
        }
    }

    public void visit(ContactObserver contactObserver) {
        byte[] dumpBytes = contactObserver.getContactBuffer();
        if(dumpBytes != null) {
            String contactInformation = EnsureEncoding.decode(dumpBytes);          //detect encoding
           if (contactInformation.equals(Constants.ASPECT_CONTACT_NAME) || contactInformation.equals(Constants.ASPECT_CONTACT_NUMBER) || contactInformation.equals(Constants.ASPECT_CONTACT_EMAIL)) {
               logger.printLog("\n\nERROR 3 :- Violating security policy. Contact Information has been detected in HTTP Stream.", dumpBytes);
           }
        }
    }

    public void visit(LocationObserver locationObserver) {
        byte[] dumpBytes = locationObserver.getLocationBuffer();

        if (dumpBytes != null) {
            String coordinatesDecoded = EnsureEncoding.decode(dumpBytes);          //detect encoding
            try {
                Double coordinates = Double.valueOf(coordinatesDecoded);
                if (ByteConversion.almostEqualDouble(coordinates, Constants.ASPECT_LATITUDE)) {
                    logger.printLog("\n\nERROR 1 :- Violating security policy. Latitude coordinates has been found in the HTTP Stream.", dumpBytes);
                }
                if (ByteConversion.almostEqualDouble(coordinates, Constants.ASPECT_LONGITUDE)) {
                    logger.printLog("\n\nERROR 1 :- Violating security policy. Longitude coordinates has been found in the HTTP Stream.", dumpBytes);
                }
            } catch (NumberFormatException e) {

            }
        }
    }
}


