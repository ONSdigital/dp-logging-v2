package com.github.onsdigital.logging.v2.example;

import com.github.onsdigital.logging.v2.LoggerConfig;
import com.github.onsdigital.logging.v2.serialiser.JacksonEventSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.onsdigital.logging.v2.example.HelloWorldLogger.logger;
import static spark.Spark.get;

public class API {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("com.github.onsdigital.logging.v2.example");
        LoggerConfig.init(logger, new JacksonEventSerializer());

        get("/hello", (req, res) -> {
            logger().uri("/hello")
                    .collectionID("666")
                    .user("dataMcDataface@ons.gov.uk")
                    .httpMethod("GET")
                    .httpPath("/hello")
                    .log("GET endpoint called");

            return "Hello World!";
        });
    }
}
