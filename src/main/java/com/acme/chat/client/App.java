package com.acme.chat.client;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger("client.App");
    private static String hostString;
    private static Integer port;

    public static void main(String[] args) {
        hostString = args[0];
        String portString = args[1];
        if (hostString == null && portString == null) showHelpAndExit();

        try {
            port = Integer.parseInt(portString);
        } catch (NumberFormatException e) {
            showHelpAndExit();
        }

        try(
            Scanner console = new Scanner(System.in);
            Connector connector = new Connector(hostString, port)
        ) {
            logger.log(Level.INFO, "Got connector");
            Controller controller = new Controller(connector);

            String consoleReadLine = null;
            while (!(consoleReadLine = console.nextLine()).equals("/quit")) {
                logger.log(Level.INFO, "Got client line");
                controller.sendMessage(consoleReadLine);
                logger.log(Level.INFO, "Message sent");

                logger.log(Level.INFO, "Reading from server");
                System.out.println(controller.getMessage());
                logger.log(Level.INFO, "Read from server");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showHelpAndExit() {
        System.out.println("Usage: App <host> <port>");
        System.exit(-1);
    }
}
