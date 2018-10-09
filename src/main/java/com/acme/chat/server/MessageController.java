package com.acme.chat.server;

import com.acme.chat.client.ChatClient;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * DDD, Eric Evance
 */
public class MessageController {
    private HistoryController historyController;
    private Collection<ChatClient> clients = new HashSet<>(); //TODO hypotesis to profile

    public MessageController(HistoryController historyController) {
        this.historyController = historyController;
    }

    /**
     *   XA resource + JTA(JavaEE) -> Saga
     */
    public void send(String message) {
        historyController.update(message);
        notifyClients(message);
    }

    public List<String> getHistory() {
        throw new UnsupportedOperationException();
    }

    public void subscribeOn(ChatClient client) {
        clients.add(client);
    }

    private void notifyClients(final String message) {
        clients.forEach(client -> client.onMessage(message));
    }
}
