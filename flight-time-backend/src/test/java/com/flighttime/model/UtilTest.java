package com.flighttime.model;

import com.flighttime.app.Utils;
import org.junit.jupiter.api.Test;

public class UtilTest {

    @Test
    public void testCalcDist() {
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
    }
}
