package com.acme.chat.server.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Acceptor implements Closeable {
    private static Logger logger = Logger.getLogger("server.Acceptor");
    private Integer port;
    private ServerSocket listener;


    public Acceptor(int port) throws IOException {
        this.port = port;
        listener = new ServerSocket(port, 50);
        logger.log(Level.INFO, "Acceptor created");
    }


    @Override
    public void close() throws IOException {
        if (listener != null) {
            listener.close();
        }
        logger.log(Level.INFO, "Acceptor closed");
    }

    public void onNewConnection(Consumer<ConnectionHandler> todo) throws IOException {
        logger.log(Level.INFO, "Client connecting...");
        try (
            Socket clientConnection = listener.accept();

            BufferedReader clientReader = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    clientConnection.getInputStream())));

            PrintWriter clientWriter = new PrintWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    clientConnection.getOutputStream())));
        ) {

            logger.log(Level.INFO, "Client handling...");
            todo.accept(new ConnectionHandler(clientConnection, clientReader, clientWriter));
            logger.log(Level.INFO, "Client handled");

        }
    }
}
