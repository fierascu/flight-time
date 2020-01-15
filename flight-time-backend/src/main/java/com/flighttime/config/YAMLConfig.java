package com.flighttime.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class YAMLConfig {

    private static final String DEFAULT_PROTOCOL = "http";

    private static final String DEFAULT_HOST = "localhost";

    private static final int DEFAULT_PORT = 8082;

    private static String protocol;

    private static String host;

    private static int port;

    YAMLConfig() {

    }

    public static String getProtocol() {
        return protocol == null ? DEFAULT_PROTOCOL : protocol;
    }

    public static void setProtocol(String protocol) {
        YAMLConfig.protocol = protocol;
    }

    public static String getHost() {
        return host == null ? DEFAULT_HOST : host;
    }

    public static void setHost(String host) {
        YAMLConfig.host = host;
    }

    public static int getPort() {
        return port == 0 ? DEFAULT_PORT : port;
    }

    public static void setPort(int port) {
        YAMLConfig.port = port;
    }

}