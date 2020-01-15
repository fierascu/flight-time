package com.flighttime.app;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flighttime.model.AirportV2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    /**
     * result in minutes
     * "30 min plus 1 hour per every 500 miles"
     * src: https://openflights.org/faq
     *
     * @param dist
     * @return
     */
    public static double calculateDurationFromMiles(double dist) {
        return 30 + 60 * dist / 500;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * @returns Distance in asked unit
     */ public static double calcDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1))
                    * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
                    * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

    public static String getDurationAsTime(double minutes) {
        Duration duration = Duration.ofMinutes((long) minutes);
        long hours = duration.toHours();
        long mins = duration.minusHours(hours).toMinutes();
        return String.format("%dhrs %02dmins", hours, mins);
    }

    /**
     * truncate a double to only two decimals
     * @param value
     * @return
     */
    public static double formatDouble(double value) {
        return Math.floor(value * 100) / 100;
    }
}
