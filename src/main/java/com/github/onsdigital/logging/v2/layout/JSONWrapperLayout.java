package com.github.onsdigital.logging.v2.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.onsdigital.logging.v2.event.BaseEvent;

import static com.github.onsdigital.logging.v2.LoggerConfig.getEventSerializer;

public class JSONWrapperLayout extends PatternLayout {

    static class JsonWrapper extends BaseEvent {

        private String json;

        protected JsonWrapper(String namespace, String json) {
            super(namespace);
            this.json = json;
        }
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        JsonWrapper wrapper = new JsonWrapper("application-stuff", event.getFormattedMessage());
        return getEventSerializer().toJson(wrapper);
    }
}
