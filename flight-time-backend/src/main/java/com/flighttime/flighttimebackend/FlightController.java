package com.flighttime.flighttimebackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FlightController {

    @RequestMapping("/flight")
    public Flight flight(@RequestParam(value = "dest", defaultValue = "") String dest, @RequestParam(value = "arr", defaultValue = "") String arr) {
        return new Flight(dest, arr);
    }

}