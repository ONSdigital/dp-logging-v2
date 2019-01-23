package com.github.onsdigital.logging.v2.serialiser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.onsdigital.logging.v2.event.BaseEvent;

public class JacksonEventSerializer implements EventSerializer {

    private final ObjectMapper mapper;

    public JacksonEventSerializer() {
        this(false);
    }

    public JacksonEventSerializer(boolean indentOutput) {
        this.mapper = new ObjectMapper();
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.mapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        if (indentOutput) {
            this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        }
    }

    @Override
    public <T extends BaseEvent> String toJson(T event) {
        try {
            return mapper.writeValueAsString(event) + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
