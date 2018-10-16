package com.acme.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector implements Closeable {
    private static Logger logger = Logger.getLogger("client.Connector");
    private Socket serverConnection;
    private BufferedReader serverReader;
    private PrintWriter serverWriter;

    public Connector(String host, int port) throws IOException {
        serverConnection = new Socket(host, port);

        serverReader = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                serverConnection.getInputStream())));

        serverWriter = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                serverConnection.getOutputStream())));

        logger.log(Level.INFO, "Connector created");

    }

    @Override
    public void close() throws IOException {
        if (serverWriter != null) {
            serverWriter.close();
        }

        IOException readerCloseException = null;
        if (serverReader != null) {
            try {
                serverReader.close();
            } catch (IOException e) {
                readerCloseException = e;
            }
        }

        IOException connectionCloseException = null;
        if (serverConnection != null) {
            try {
                serverConnection.close();
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

        logger.log(Level.INFO, "Connector closed");
    }

    public BufferedReader getServerReader() {
        return serverReader;
    }

    public PrintWriter getServerWriter() {
        return serverWriter;
    }
}
