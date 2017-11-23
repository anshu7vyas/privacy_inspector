package visitor;

import util.Constants;
import util.SlidingBuffer;

/**
 * Visitable class for Geolocation - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class LocationObserver implements Visitable {

    private byte[] locationBuffer;

    private static LocationObserver locationObserver = null;

    public static LocationObserver getInstance(Object object) {
        if (locationObserver == null) {
            locationObserver = new LocationObserver(object);
        }
        return locationObserver;
    }

    private LocationObserver(Object object) {
        locationBuffer = new byte[Constants.DOUBLE_BYTE_SIZE];
    }


    public void updateBuffer(Object object) {
        SlidingBuffer.getInstance(object).fillBuffer(locationBuffer);
    }


    /**
     * Accepts the visitor - DataInspector
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * @return locationBuffer
     */
    public byte[] getLocationBuffer() {
        return locationBuffer;
    }
}
