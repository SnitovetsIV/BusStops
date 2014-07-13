package by.snitovets.busstops.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Илья on 10.07.2014.
 */
public class Bus implements Runnable {
    private static final Logger LOG = Logger.getLogger(Bus.class);
    //маршрут движения автобуса
    private List<BusStop> route;
    private int length;     //m

    public Bus(int length) {
        this.length = length;
    }

    public void setRoute(List<BusStop> clctn) {
        route = clctn;
    }

    public void addAllBusStops(Collection<? extends BusStop> clctn) {
        if (null == route) {
            route = new ArrayList<>();
        }
        route.addAll(clctn);
    }

    @Override
    public void run() {
        for (BusStop busStop : route) {
            try {
                //надо же доехать до остановки
                long goingTime = Math.round(1000 * Math.random());
                LOG.info("Bus " + Thread.currentThread().getId() + " going to stop (" + goingTime + ")");
                Thread.sleep(goingTime);
                //заезд на остановку
                busStop.tryEnter(length);
                //посадка и высадка пассажиров
                long stopTime = Math.round(1000 * Math.random());
                LOG.info("Bus " + Thread.currentThread().getId() + " standing at the bus stop (" + stopTime + ")");
                Thread.sleep(stopTime);
                //выезд из остановки
                busStop.leave(length);
            } catch (InterruptedException ex) {
                LOG.error(ex.getMessage());
            }
        }
    }

}
