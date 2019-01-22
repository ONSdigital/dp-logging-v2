package com.github.onsdigital.logging.v2;

public interface LogWriter<T extends Event> {

    void log(T event);

    void close();
}
