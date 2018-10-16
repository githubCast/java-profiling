package com.acme.chat.server;

import com.acme.chat.Chat;
import com.acme.chat.server.history.FileHistoryService;
import com.acme.chat.server.net.ConnectionHandler;

import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * DDD, Eric Evance
 */
public class Controller implements Chat {
    private static Logger logger = Logger.getLogger("server.Controller");
    private FileHistoryService historyService;
    private Collection<ConnectionHandler> clients = new HashSet<>(); //TODO design to profile

    public Controller(FileHistoryService historyService) {
        this.historyService = historyService;
        logger.log(Level.INFO, "Got controller instance");
    }

    /**
     *   XA resource + JTA(JavaEE) -> Saga
     */
    @Override
    public void sendMessage(String message) {
        logger.log(Level.INFO, "Message sending...");
        historyService.update(message);
        notifyClients(message);
        logger.log(Level.INFO, "Message sent to all");
    }

    @Override
    public Stream<String> getHistory() {
        throw new UnsupportedOperationException();
    }

    public void subscribe(ConnectionHandler client) {
        clients.add(client);
    }

    public void notifyClients(final String message) {
        clients.forEach(client -> client.onMessage(message));
    }

    public void unsubscribe(ConnectionHandler client) {
        clients.remove(client);
    }
}
