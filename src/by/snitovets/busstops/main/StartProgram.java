package by.snitovets.busstops.main;

import by.snitovets.busstops.entity.Bus;
import by.snitovets.busstops.entity.BusStop;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;

/**
 * Created by Илья on 10.07.2014.
 */
public class StartProgram {


    static {
        new DOMConfigurator().doConfigure("log4j.xml", LogManager.getLoggerRepository());
    }

    public static void main(String... argv) {
        ArrayList<BusStop> busStops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            busStops.add(new BusStop(i*4, "bus stop " + String.valueOf(i)));
        }
        for (int i = 0; i < 5; i++) {
            Bus bus = new Bus(i*2);
            bus.addAllBusStops(busStops);
            new Thread(bus).start();
        }
    }


}
