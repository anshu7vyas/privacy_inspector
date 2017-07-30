package visitor;

import util.ByteConversion;
import util.Constants;
import util.EnsureEncoding;
import util.Logging;

/**
 * Created by av7 on 7/29/17.
 */
public class SecurityChecker implements Visitor {
    private static SecurityChecker securityChecker = new SecurityChecker();
    Logging logger = Logging.getInstance();

    private SecurityChecker() {

    }
    public static SecurityChecker getInstance() {
        return securityChecker;
    }

    public void visit(IMEIInfo imeiInfo) {
        byte[] dumpBytes = imeiInfo.getIMEIBuffer();
        if(dumpBytes != null) {
            String decoded = EnsureEncoding.decode(dumpBytes);          //detect encoding
            if (decoded.equals(Constants.ASPECT_IMEI)) {
                    // print message
                logger.printLog("\n\nViolating security policy. Dummy IMEI number have been found at the sink. Error 2", dumpBytes);
            }
        }
    }

    public void visit(ContactInfo contactInfo) {
        byte[] dumpBytes = contactInfo.getContactBuffer();
        if(dumpBytes != null) {
            String viewEncoding = EnsureEncoding.decode(dumpBytes);          //detect encoding
           /* if (transformedDump.equals(Constants.)) {
                    // print message
                }
            }*/
        }
    }

    public void visit(LocationInfo locationInfo) {
       /* byte[] dumpBytes = locationInfo.getLocationBuffer();
        if(dumpBytes != null) {
            String viewEncoding = EnsureEncoding.decode(dumpBytes);          //detect encoding
            //System.out.println("detected encoding = " + viewEncoding);
            if (viewEncoding == Constants.UTF_8_ENCODING) {
                String transformedDump = ByteConversion.byte2String(dumpBytes);
                if (transformedDump.equals(Constants.ASPECT_IMEI)) {
                    // print message
                }
            }
        }*/
    }
}
