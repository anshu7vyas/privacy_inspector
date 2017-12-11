package observer;

import util.SlidingBuffer;
import visitor.*;

import java.util.*;

/**
 * A singleton class that manages all the Sliding Buffers required for analysis.
 *
 * @author Anshul Vyas
 */
public class BufferManager implements Observer {

    // Single globalBuffer instance per BufferManager instance
    public SlidingBuffer globalBuffer; //= SlidingBuffer.getInstance();

    // Arraylist of Visitables
    private List<Visitable> visitables = new ArrayList<Visitable>();

    // A hashmap to keep record of different buffer managers for different output streams
    private static Map<Object, BufferManager> internalMap = new HashMap<Object, BufferManager>();

    private BufferManager() { instantiateVisitables();}

    /**
     * Ensures single instance
     * @return instance of BufferManager according to the Object in the internalMap
     */
    public static BufferManager getInstance(Object object) {
        if (!internalMap.containsKey(object)){
            internalMap.put(object, new BufferManager());
        }
        return internalMap.get(object);
    }

    /**
     *  Instantiates the custom sources/plugins and adds to the Arraylist<Visitable>
     */
    public void instantiateVisitables() {
        globalBuffer = SlidingBuffer.getInstance();

        IMEIObserver imeiObserver = IMEIObserver.getInstance();
        visitables.add(imeiObserver);

        LocationObserver locationObserver = LocationObserver.getInstance();
        visitables.add(locationObserver);

        ContactObserver contactObserver = ContactObserver.getInstance();
        visitables.add(contactObserver);
    }

    /**
     * Updates sliding buffers for each visitable and runs the analysis
     * @param object - signifying an object of a particular OutputStream
     */
    @Override
    public void update(Object object) {
        for (Visitable visitable: visitables) {
            visitable.updateBuffer(object);
            visitable.accept(DataInspector.getInstance());
        }
    }
}

// Observer parent - BufferManager -> visitables are his children. He notifies them once he gets to know.