package com.flighttime.repository;

import com.flighttime.app.Utils;
import com.flighttime.model.Airport;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AirportRepository {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    private static final String FILE_NAME = "world-airports.csv";

    AirportRepository() {
    }

    public static List<Airport> csvProcessing() {
        List<Airport> airports = new ArrayList<>();
        ClassLoader classLoader = new AirportRepository().getClass().getClassLoader();

        File file = new File(classLoader.getResource(FILE_NAME).getFile());

        if (file.exists()) {
            airports = processInputFile(file.getAbsolutePath());
        }

        logger.info("Loaded " + airports.size() + " airports.");
        return airports;
    }

    private static List<Airport> processInputFile(String inputFilePath) {
        List<Airport> airports = new ArrayList<>();
        try (Reader in = new FileReader(inputFilePath)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(in);
            for (CSVRecord record : records) {
                Airport airport = new Airport();

                airport.setId(Integer.parseInt(record.get("id")));
                airport.setType(record.get("type"));
                airport.setName(record.get("name"));
                airport.setLatitude_deg(Utils.getSafeDouble(record.get("latitude_deg")));
                airport.setLongitude_deg(Utils.getSafeDouble(record.get("longitude_deg")));
                airport.setMunicipality(record.get("municipality"));
                airport.setGps_code(record.get("gps_code"));
                airport.setIata_code(record.get("iata_code"));
                airport.setLocal_code(record.get("local_code"));
                airports.add(airport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return airports;
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
