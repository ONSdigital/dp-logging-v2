package com.github.onsdigital.logging.v2;

import com.github.onsdigital.logging.v2.serialiser.EventSerializer;
import org.slf4j.Logger;

public class LoggerConfig {

    private static Logger LOGGER;
    private static EventSerializer EVENT_SERIALIZER;

    public static void init(Logger logger, EventSerializer eventSerializer) {
        LOGGER = logger;
        EVENT_SERIALIZER = eventSerializer;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static EventSerializer getEventSerializer() {
        return EVENT_SERIALIZER;
    }
}
