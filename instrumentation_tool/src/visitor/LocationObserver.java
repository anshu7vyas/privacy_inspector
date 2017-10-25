package visitor;

/**
 * Visitable class for Geolocation - defines an accept method that accepts visitor objects
 *
 * @author Anshul Vyas
 */
public class LocationObserver implements Visitable {

    private byte[] locationBuffer;

    public LocationObserver(byte[] locationBuffer) {
        this.locationBuffer = locationBuffer;
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
