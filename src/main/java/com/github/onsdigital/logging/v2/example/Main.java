package com.github.onsdigital.logging.v2.example;

import com.github.onsdigital.logging.v2.JacksonConsoleLogWriter;
import com.github.onsdigital.logging.v2.LogWriter;
import org.slf4j.MDC;

import java.util.UUID;

import static com.github.onsdigital.logging.v2.example.ZebedeeLogger.logger;

public class Main {

    public static void main(String[] args) {
        LogWriter logWriter = new JacksonConsoleLogWriter(System.out);

        MDC.put("trace_id", UUID.randomUUID().toString());

        ZebedeeLogger.init(logWriter);

        logger().user("test@ons.gov.uk")
                .uri("/economy/12345")
                .collectionID("666")
                .httpMethod("POST")
                .httpPath("/saveCollection")
                .log("successfully saved collection");


        logger().user("test@ons.gov.uk")
                .uri("/economy/12345")
                .httpMethod("POST")
                .httpPath("/publish")
                .log("publish collection request submitted");

        logger().user("test@ons.gov.uk")
                .uri("/economy/12345")
                .httpMethod("POST")
                .httpPath("/publish")
                .log("publish collection request completed succssfully");
    }
}
