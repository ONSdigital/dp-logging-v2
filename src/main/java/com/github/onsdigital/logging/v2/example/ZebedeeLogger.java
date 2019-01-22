package com.github.onsdigital.logging.v2.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.onsdigital.logging.v2.Event;
import com.github.onsdigital.logging.v2.LogWriter;

import java.util.HashMap;
import java.util.Map;

public class ZebedeeLogger extends Event<ZebedeeLogger> {

    static LogWriter logWriter;

    public ZebedeeLogger() {
        super("zebedee.cms");
    }

    @JsonProperty("zebedee.data")
    private Map<String, Object> zebedeeData = new HashMap<>();

    protected LogWriter getLogWriter() {
        return logWriter;
    }

    public ZebedeeLogger user(String name) {
        this.zebedeeData.put("name", name);
        return this;
    }

    public ZebedeeLogger collectionID(String id) {
        this.zebedeeData.put("collectionID", id);
        return this;
    }

    public ZebedeeLogger uri(String uri) {
        this.zebedeeData.put("uri", uri);
        return this;
    }


    public static void init(LogWriter lw) {
        logWriter = lw;
    }

    public static ZebedeeLogger logger() {
        return new ZebedeeLogger();
    }

}
