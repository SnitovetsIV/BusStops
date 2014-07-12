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
    private final int maxLength = 10;

    public BusStop(int maxCountOfBus, String name) {
        this.name = name;
        semaphore = new Semaphore(maxCountOfBus);
    }

    public BusStop() {
        this(2, "noname bus stop");
    }

    public void enter() {
        try {
            semaphore.acquire();
            LOG.info("Bus " + Thread.currentThread().getId() + " enter" + name);
        } catch (InterruptedException ex) {
        }
    }


    public void leave() {
        semaphore.release();
        LOG.info("Bus " + Thread.currentThread().getId() + " leave" + name);
    }

}
