package pager.demo.com.fragment;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by user2 on 27-02-2018.
 */

public class EventBusInit {
    private static Bus bus=new Bus(ThreadEnforcer.MAIN);

    public static Bus getBus() {
        return bus;
    }




}
