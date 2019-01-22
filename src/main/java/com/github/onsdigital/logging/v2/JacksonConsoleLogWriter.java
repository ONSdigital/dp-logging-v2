package com.github.onsdigital.logging.v2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.OutputStream;
import java.io.PrintWriter;

public class JacksonConsoleLogWriter implements LogWriter {

    private PrintWriter printWriter;
    private ObjectMapper mapper;

    public JacksonConsoleLogWriter(OutputStream outputStream) {
        this.printWriter = new PrintWriter(outputStream);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                close();
            }
        });

        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public void log(Event event) {
        try {
            printWriter.println(mapper.writeValueAsString(event));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        printWriter.close();
    }
}
