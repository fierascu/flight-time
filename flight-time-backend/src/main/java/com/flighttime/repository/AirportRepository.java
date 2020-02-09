package com.flighttime.repository;

import com.flighttime.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AirportRepository {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static final String COMMA = ",";

    // TODO airport code and icao should be stored as lowercase or uppercase to omit one transformation;
    // Probably uppercase because the frontend should uppercase the response?
    AirportRepository() {
    }

    public static List<Airport> csvProcessing() {
        List<Airport> airports = processInputFile("world-airports.csv");
        logger.info("Loaded " + airports.size() + " airports.");
        return airports;
    }

    private static List<Airport> processInputFile(String inputFilePath) {
        List<Airport> inputList = new ArrayList<>();
        try (InputStream in = AirportRepository.class
                .getClassLoader()
                .getResourceAsStream(inputFilePath);
             InputStreamReader reader = new InputStreamReader(in)) {
            BufferedReader br = new BufferedReader(reader);
            // skip the header of the csv
            inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        inputList.removeAll(Collections.singleton(null));
        return inputList;
    }


    private static Function<String, Airport> mapToItem = (line) -> {
        String[] p = line.split(COMMA);// a CSV has comma separated lines
        Airport item = new Airport();
        item.setId(Integer.parseInt(p[0]));
        if (p[1] != null && p[1].trim().length() > 0) {
            item.setIdent("" + p[1]);
        }
        if (p[2] != null && p[2].trim().length() > 0) {
            item.setType("" + p[2]);
        }
        if (p[3] != null && p[3].trim().length() > 0) {
            item.setContinent("" + p[3]);
        }
        if (p[4] != null && p[4].trim().length() > 0) {
            item.setLatitude_deg(getSafeDouble(p[4]));
        }
        if (p[5] != null && p[5].trim().length() > 0) {
            item.setLongitude_deg(getSafeDouble(p[5]));
        }


        if (p[12] != null && p[12].trim().length() > 0) {
            item.setGps_code("" + p[12]);
        }

        if (p[13] != null && p[13].trim().length() > 0) {
            item.setIata_code("" + p[13]);
        }

        return item;
    };

    private static double getSafeDouble(String doubleValue) {
        try {
            return Double.parseDouble(doubleValue);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static Airport findAirportByCode(List<Airport> airports, String code) {
        return airports.stream().filter(a -> a.getIata_code().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    public static Airport findAirportByIcao(List<Airport> airports, String icao) {
        return airports.stream().filter(a -> a.getGps_code().equalsIgnoreCase(icao)).findFirst().orElse(null);
    }

    public static Airport findAirportContainingName(List<Airport> airports, String name) {
        return airports.stream().filter(a -> a.getName().toLowerCase().contains(name.toLowerCase())).findFirst()
                .orElse(null);
    }

    public static List<Airport> findAirportWildcard(List<Airport> airports, String wildcard) {
        return airports.stream().filter(
                a -> a.getIata_code().toLowerCase().equalsIgnoreCase(wildcard.toLowerCase())
                        || a.getGps_code().toLowerCase().equalsIgnoreCase(wildcard.toLowerCase())
                        || a.getName().toLowerCase().contains(wildcard.toLowerCase())
                        || a.getMunicipality().toLowerCase().contains(wildcard.toLowerCase()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
