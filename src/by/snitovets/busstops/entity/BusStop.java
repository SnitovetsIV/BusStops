package by.snitovets.busstops.entity;

import org.apache.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by Илья on 10.07.2014.
 */
public class BusStop {

    private static final Logger LOG = Logger.getLogger(BusStop.class);
    private final String name;
    private final Semaphore semaphore;
    private final int maxLength = 10;       // m

    public BusStop(int maxLength, String name) {
        this.name = name;
        semaphore = new Semaphore(maxLength);
    }

    public BusStop() {
        this(2, "noname bus stop");
    }

    public boolean tryEnter(int length) {
        boolean result = false;
        if (semaphore.tryAcquire(length)) {
            LOG.info("Bus " + Thread.currentThread().getId() + " enter" + name);
            result = true;
        }
        return result;
    }

    public void enter(int length) {
        try {
            semaphore.acquire(length);
            LOG.info("Bus " + Thread.currentThread().getId() + " enter" + name);
        } catch (InterruptedException ex) {
            LOG.error(ex.getMessage());
        }
    }


    public void leave(int length) {
        semaphore.release(length);
        LOG.info("Bus " + Thread.currentThread().getId() + " leave" + name);
    }

}
