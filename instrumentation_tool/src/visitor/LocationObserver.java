package visitor;

/**
 * Created by av7 on 7/29/17.
 */
public class LocationObserver implements Visitable {

    private byte[] locationBuffer;

    public LocationObserver(byte[] locationBuffer) {
        this.locationBuffer = locationBuffer;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public byte[] getLocationBuffer() {
        return locationBuffer;
    }
}
