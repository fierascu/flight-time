package com.flighttime.app;

import java.text.Normalizer;
import java.time.Duration;

public class Utils {

    Utils() {
    }

    /**
     * result in minutes
     * "30 min plus 1 hour per every 500 miles"
     * src: https://openflights.org/faq
     *
     * @param dist distance im miles
     * @return time in minutes
     */
    public static double calculateDurationFromMiles(double dist) {
        return 30 + 60 * dist / 500;
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     *
     * @return distance in asked unit
     */
    public static double calcDistance(double lat1, double lon1, double lat2, double lon2, String unit) {
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
     *
     * @param value in double format
     * @return same value with 2 decimals precision
     */
    public static double formatDouble(double value) {
        return Math.floor(value * 100) / 100;
    }

    public static String normalize(String subjectString) {
        subjectString = Normalizer.normalize(subjectString, Normalizer.Form.NFD);
        return subjectString.replaceAll("[^\\x00-\\x7F]", "");
    }

    public static double getSafeDouble(String doubleValue) {
        try {
            return Double.parseDouble(doubleValue);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
