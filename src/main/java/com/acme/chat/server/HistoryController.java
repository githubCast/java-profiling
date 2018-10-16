package com.acme.chat.server;

import java.io.BufferedReader;
import java.io.Closeable;
import java.util.Collection;
import java.util.stream.Stream;

public class HistoryController {
    public void update(String message) {
        try (BufferedReader br = getPooledReader()) {

        } //br.close()
    }

    public Stream<String> getHistory() {
        try (BufferedReader br = ???) {

        }
    }

}
