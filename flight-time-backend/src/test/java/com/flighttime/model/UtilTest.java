package com.flighttime.model;

import com.flighttime.app.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @Test
    public void testCalcDist() {
        assertEquals(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "M"), 262.67, 1);
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "M") + " Miles\n");

        assertEquals(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "K"), 422.73, 1);
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "K") + " Kilometers\n");

        assertEquals(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "N"), 228.10, 1);
        System.out.println(Utils.calcDistance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
    }


    @Test
    public void testNormalize() {
        assertEquals(Utils.normalize("abcd"), "abcd");
        assertEquals(Utils.normalize("öäü"), "oau");
        assertEquals(Utils.normalize("12"), "12");
        assertEquals(Utils.normalize("Timişoara"), "Timisoara");
    }


}
