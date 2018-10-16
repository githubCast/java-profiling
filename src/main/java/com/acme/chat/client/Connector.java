package com.acme.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Connector {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        try (
                Socket serverConnection = new Socket("localhost", 6666);
                BufferedReader serverReader = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        serverConnection.getInputStream())));

                PrintWriter serverWriter = new PrintWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(
                                        serverConnection.getOutputStream())));
        ) {

            while (true) {
                String consoleReadLine = console.nextLine();
                System.out.println("DEBUG: " + consoleReadLine);

                serverWriter.println(consoleReadLine);
                serverWriter.flush();

                System.out.println("DEBUG: " + "string sent");
                System.out.println(serverReader.readLine());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
