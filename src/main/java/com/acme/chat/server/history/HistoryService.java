package com.acme.chat.server.history;

import java.io.IOException;
import java.util.stream.Stream;

public interface HistoryService {
    void update(String message);
    Stream<String> get() throws IOException;
}
