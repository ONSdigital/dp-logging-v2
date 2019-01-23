package com.github.onsdigital.logging.v2;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.github.onsdigital.logging.v2.LoggerConfig.getEventSerializer;
import static com.github.onsdigital.logging.v2.LoggerConfig.getLogger;

public abstract class BaseEvent<T extends BaseEvent> {

    static final String TRACE_ID = "trace_id";
    static final String SPAN_ID = "span_id";
    static final String HTTP_METHOD = "method";
    static final String HTTP_PATH = "path";

    protected String event;
    private Date created;
    private String namespace;
    protected String traceID;
    protected String spanID;
    private Map<String, Object> http;

    protected BaseEvent(String namespace) {
        this.namespace = namespace;
        this.created = new Date();
        this.http = new HashMap<>();
    }

    public String namespace() {
        return namespace;
    }

    public T traceID(String traceID) {
        this.traceID = traceID;
        return (T) this;
    }

    public T spanID(String spanID) {
        this.spanID = spanID;
        return (T) this;
    }

    public T httpMethod(String method) {
        this.http.put(HTTP_METHOD, method);
        return (T) this;
    }

    public T httpPath(String path) {
        this.http.put(HTTP_PATH, path);
        return (T) this;
    }

    public void log(String event) {
        this.event = event;

        if (StringUtils.isEmpty(traceID)) {
            this.traceID = MDC.get(TRACE_ID);
        }

        if (StringUtils.isEmpty(spanID)) {
            this.spanID = MDC.get(SPAN_ID);
        }

        getLogger().info(getEventSerializer().toJson(this));
    }
}
