package com.acme.chat.server.net;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionHandler implements Closeable  {
    private static Logger logger = Logger.getLogger("server.ConnectionHandler");

    private final Socket clientConnection;
    private final BufferedReader clientReader;
    private final PrintWriter clientWriter;

    public ConnectionHandler(Socket clientConnection, BufferedReader clientReader, PrintWriter clientWriter) {
        this.clientConnection = clientConnection;
        this.clientReader = clientReader;
        this.clientWriter = clientWriter;
        logger.log(Level.INFO, "Created ConnectionHandler");
    }

    public PrintWriter getClientWriter() {
        return clientWriter;
    }

    public BufferedReader getClientReader() {
        return clientReader;
    }

    public void onMessage(String message) {
        logger.log(Level.INFO, "Message sending...");
        clientWriter.println(message);
        clientWriter.flush();
        logger.log(Level.INFO, "Message sent");
    }

    @Override
    public void close() throws IOException {
        if (clientReader != null) {
            clientReader.close();
        }

        IOException readerCloseException = null;
        if (clientReader != null) {
            try {
                clientReader.close();
            } catch (IOException e) {
                readerCloseException = e;
            }
        }

        IOException connectionCloseException = null;
        if (clientConnection != null) {
            try {
                clientConnection.close();
            } catch (IOException e) {
                connectionCloseException = e;
            }
        }

        if (connectionCloseException != null) {
            if (readerCloseException != null) connectionCloseException.addSuppressed(readerCloseException);
            throw connectionCloseException;
        } else {
            if (readerCloseException != null) throw connectionCloseException;
        }

        logger.log(Level.INFO, "ConnectionHandler closed");
    }
}
