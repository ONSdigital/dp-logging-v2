package com.github.onsdigital.logging.v2.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.onsdigital.logging.v2.event.BaseEvent;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldLogger extends BaseEvent<HelloWorldLogger> {

    public HelloWorldLogger() {
        super("hello.world");
    }

    @JsonProperty("hello.world.data")
    private Map<String, Object> zebedeeData = new HashMap<>();


    public HelloWorldLogger user(String name) {
        this.zebedeeData.put("name", name);
        return this;
    }

    public HelloWorldLogger collectionID(String id) {
        this.zebedeeData.put("collectionID", id);
        return this;
    }

    public HelloWorldLogger uri(String uri) {
        this.zebedeeData.put("uri", uri);
        return this;
    }

    public static HelloWorldLogger logger() {
        return new HelloWorldLogger();
    }
}
