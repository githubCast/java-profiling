package com.acme.chat.client;

import com.acme.chat.Chat;

import java.io.IOException;
import java.util.stream.Stream;

public class Controller implements Chat {
    private Connector connector;

    public Controller(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void sendMessage(String message) {
        connector.getServerWriter().println(message);
        connector.getServerWriter().flush();
    }

    @Override
    public Stream<String> getHistory() {
        return null;
    }

    public String getMessage() throws IOException {
        return connector.getServerReader().readLine();
    }
}
