package com.acme.chat.server.protocol;

import com.acme.chat.server.Controller;
import com.acme.chat.server.net.ConnectionHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatProtocolHandler {
    private static Logger logger = Logger.getLogger("server.ProtocolHandler");
    private ConnectionHandler connectionHandler;
    private Controller controller;

    public ChatProtocolHandler(ConnectionHandler connectionHandler, Controller controller) {
        this.connectionHandler = connectionHandler;
        this.controller = controller;
        logger.log(Level.INFO, "New instance");
    }

    public void handle() {
        logger.log(Level.INFO, "Client handling begins");
        try (ConnectionHandler connection = connectionHandler) {
            controller.subscribe(connectionHandler);
            logger.log(Level.INFO, "Client put in collection");

            String clientReadLine = null;
            while ((clientReadLine = connection.getClientReader().readLine()) != null) {
                logger.log(Level.INFO, "Got message " + clientReadLine);
                controller.sendMessage(clientReadLine);
                logger.log(Level.INFO, "Message sent to all");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            controller.unsubscribe(connectionHandler);
            logger.log(Level.INFO, "Client put off collection");
        }



    }
}
