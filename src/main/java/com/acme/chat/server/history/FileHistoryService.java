package com.acme.chat.server.history;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileHistoryService implements HistoryService, Closeable {
    private static Logger logger = Logger.getLogger("server.FileHistoryService");
    private File historyFilePath;
    private final BufferedReader historyReader;
    private final PrintWriter historyWriter;


    public FileHistoryService(String historyFileName) throws FileNotFoundException {
        historyFilePath = new File(historyFileName);

        historyWriter = new PrintWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                new FileOutputStream(historyFilePath))));

        historyReader = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                new FileInputStream(historyFilePath))));

        logger.log(Level.INFO, "Created");
    }

    @Override
    public void update(String message) {
        logger.log(Level.INFO, "Updating history...");
        historyWriter.println(message);
        historyWriter.flush();
        logger.log(Level.INFO, "History updated");
    }

    @Override
    public Stream<String> get() throws IOException {
        return Files.lines(Paths.get(historyFilePath.toURI()));
    }

    @Override
    public void close() throws IOException {
        if (historyReader != null) historyReader.close();
        if (historyWriter != null) historyWriter.close();
        logger.log(Level.INFO, "Closed");
    }
}
