package com.flighttime.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flighttime.model.AirportV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AirportRepository {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    // TODO airport code and icao should be stored as lowercase or uppercase to omit one transformation;
    // Probably uppercase because the frontend should uppercase the response?
    AirportRepository() {
    }

    public static List<AirportV2> jsonProcessing() {
        List<AirportV2> airports = new ArrayList<>();
        try {
            Resource resource = new ClassPathResource("airports_v2.json");

            InputStream input = resource.getInputStream();
            String jsonString;
            try (Scanner scanner = new Scanner(input, StandardCharsets.UTF_8.name())) {
                jsonString = scanner.useDelimiter("\\A").next();
            }

            ObjectMapper mapper = new ObjectMapper();
            airports = mapper.readValue(jsonString,
                    new TypeReference<ArrayList<AirportV2>>() {
                    });
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return airports;
    }

    public static AirportV2 findAirportByCode(List<AirportV2> airports, String code) {
        return airports.stream().filter(a -> a.getCode().equalsIgnoreCase(code)).findFirst().orElse(null);
    }

    public static AirportV2 findAirportByIcao(List<AirportV2> airports, String icao) {
        return airports.stream().filter(a -> a.getIcao().equalsIgnoreCase(icao)).findFirst().orElse(null);
    }

    public static AirportV2 findAirportContainingName(List<AirportV2> airports, String name) {
        return airports.stream().filter(a -> a.getName().toLowerCase().contains(name.toLowerCase())).findFirst()
                .orElse(null);
    }

    public static List<AirportV2> findAirportWildcard(List<AirportV2> airports, String wildcard) {
        return airports.stream().filter(
                a -> a.getCode().toLowerCase().equalsIgnoreCase(wildcard.toLowerCase())
                        || a.getIcao().toLowerCase().equalsIgnoreCase(wildcard.toLowerCase())
                        || a.getName().toLowerCase().contains(wildcard.toLowerCase()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
