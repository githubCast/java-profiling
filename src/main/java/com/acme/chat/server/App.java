package com.acme.chat.server;

import com.acme.chat.server.history.FileHistoryService;
import com.acme.chat.server.net.Acceptor;
import com.acme.chat.server.protocol.ChatProtocolHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger("server.App");
    private static Integer port;

    public static void main(String[] args) {
        if (args[0] == null) showHelpAndExit();

        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            showHelpAndExit();
        }

        try (
            Acceptor acceptor = new Acceptor(port);
            FileHistoryService historyService = new FileHistoryService("history.txt");
        ) {
            logger.log(Level.INFO, "Got Acceptor");
            Controller controller = new Controller(historyService);

            while (true) {
                try {
                    acceptor.onNewConnection(connectionHandler -> {
                        logger.log(Level.INFO, "Got Connection");
                        new ChatProtocolHandler(connectionHandler, controller).handle();
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showHelpAndExit() {
        System.out.println("Usage: App <port>");
    }
}
