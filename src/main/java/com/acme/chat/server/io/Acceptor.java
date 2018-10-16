package com.acme.chat.server.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor {
    public static void main(String[] args) {
        try(
            ServerSocket listener = new ServerSocket(6666, 50);
        ) {
//            listener.setSoTimeout(1_000);

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
                System.out.println("DEBUG: connected " + clientConnection.getPort());

                String clientReadLine = null;
                while ((clientReadLine = clientReader.readLine()) != null) {
                    System.out.println("DEBUG: read " + clientReadLine);

                    String sentLine = ">>>" + clientReadLine;
                    clientWriter.println(sentLine);
                    clientWriter.flush();
                    System.out.println("DEBUG: sent " + sentLine);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
