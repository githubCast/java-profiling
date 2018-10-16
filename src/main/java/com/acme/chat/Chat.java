package com.acme.chat;

import java.util.stream.Stream;

public interface Chat {
    void sendMessage(String message);
    Stream<String> getHistory();
}
