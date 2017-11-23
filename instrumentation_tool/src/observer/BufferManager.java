package observer;

import util.Constants;
import util.SlidingBuffer;
import visitor.*;

import java.nio.Buffer;
import java.util.*;

/**
 * A singleton class that manages all the Sliding Buffers required for analysis.
 *
 * @author Anshul Vyas
 */
public class BufferManager implements Observer {

    //private static BufferManager bufferManager; //= new BufferManager();//null;
    public SlidingBuffer slidingBuffer = SlidingBuffer.getInstance();


    /**
     * Sources to analyze
     */
    IMEIObserver imeiObserver;
    LocationObserver locationObserver;
    ContactObserver contactObserver;

    /**
     * Arraylist of Visitables
     */
    private List<Visitable> visitables = new ArrayList<Visitable>();

    /* A hashmap to keep record of different buffer managers for different output streams */
    private static Map<Object, BufferManager> internalMap = new HashMap<Object, BufferManager>();

    private BufferManager() { instantiateVisitables();}

    /**
     * @return a new instance of the BufferManager
     */
    public static BufferManager getInstance(Object object) {
//        if (bufferManager == null) {
//            bufferManager = new BufferManager();
//        }
//
//        return bufferManager;
        if (!internalMap.containsKey(object)){
            internalMap.put(object, new BufferManager());

        }
        return internalMap.get(object);
    }

    public void instantiateVisitables() {
        imeiObserver = imeiObserver.getInstance();
        visitables.add(imeiObserver);

        locationObserver = locationObserver.getInstance();
        visitables.add(locationObserver);

        contactObserver = contactObserver.getInstance();
        visitables.add(contactObserver);
    }

    /**
     * Overrides the update() method of Observer Interface to get new Sliding buffers for each of 3 analysis,
     * namely, IMEI, Geolocation, Contacts
     *
     *
     */
    @Override
    public void update(Object object) {

        for (Visitable visitable: visitables) {
            visitable.updateBuffer(object);
            visitable.accept(DataInspector.getInstance());
        }

        //IMEIObserver imeiObserver = new IMEIObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.SLIDING_WINDOW_SIZE));
        //imeiObserver.accept(DataInspector.getInstance());

        //LocationObserver locationObserver = new LocationObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.DOUBLE_BYTE_SIZE));
        //locationObserver.accept(DataInspector.getInstance());

        //ContactObserver contactObserver = new ContactObserver(SlidingBuffer.getInstance(object).getCircularBuffer(Constants.CONTACT_INFO_BYTE_SIZE));
        //contactObserver.accept(DataInspector.getInstance());

    }

}

// Observer parent - BufferManager -> visitables are his children. He notifies them once he gets to know.