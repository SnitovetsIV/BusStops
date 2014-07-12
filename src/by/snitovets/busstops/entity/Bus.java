package by.snitovets.busstops.entity;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Илья on 10.07.2014.
 */
public class Bus implements Runnable {
    //маршрут движения автобуса
    private List<BusStop> route;
    private static final Logger LOG = Logger.getLogger(Bus.class);

    public void setRoute(Collection<? extends BusStop> clctn) {
        route = (List<BusStop>) clctn;
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
                long gointTime = Math.round(1000 * Math.random());
                LOG.info("Bus " + Thread.currentThread().getId() + " going to stop (" + gointTime + ")");
                Thread.sleep(gointTime);
                //заезд на остановку
                busStop.enter();
                //посадка и высадка пассажиров
                long stopTime = Math.round(1000 * Math.random());
                LOG.info("Bus " + Thread.currentThread().getId() + " standing at the bus stop (" + stopTime + ")");
                Thread.sleep(stopTime);
                //выезд из остановки
                busStop.leave();
            } catch (InterruptedException ex) {

            }
        }
    }

}
